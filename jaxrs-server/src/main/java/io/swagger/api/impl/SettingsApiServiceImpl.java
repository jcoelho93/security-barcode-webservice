package io.swagger.api.impl;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import io.swagger.api.*;
import io.swagger.model.*;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.Setting;
import java.util.Date;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import custom.*;
import static io.swagger.util.PrimitiveType.URI;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import org.bson.Document;
import org.bson.types.ObjectId;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-01T17:42:32.367Z")
public class SettingsApiServiceImpl extends SettingsApiService {
    
    /**
     * Gets some or all settings matching a defined query
     * @param size
     * @param date
     * @param algorithm
     * @param barcode
     * @param securityContext
     * @return
     * @throws NotFoundException 
     */
    @Override
    public Response settingsGet(Integer size, String date, String algorithm, String barcode, SecurityContext securityContext, UriInfo uriinfo)
    throws NotFoundException {
        
        long requestTime = System.currentTimeMillis();
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("barcodes");
        
        //Defining query
        FindIterable<Document> search = null;
        Document query = new Document();
        EventLogger logger = new EventLogger(mongoClient);
        // If client defined a date
        if(date != null){
            DateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
            Date new_date = null;
            try{
                new_date = date_format.parse(date);
            }catch(Exception e){
                logger.log(new ApiEvent("GET", uriinfo.getPath(), new Date(requestTime), 400, "Bad request", "Cannot parse date", new Date(System.currentTimeMillis()), Util.stackTraceToString(e)));
                return Response.status(400).entity(new Document("message", "Cannot parse date. Use dd-MM-yyyy")).build();
            }
            query.append("created_at", date_format.format(new_date));
        }
        // If client defined an algorithm
        if(algorithm != null){
            query.append("algorithm",algorithm);
        }
        // If client defined an array size
        if(size != null && size > 0){
            search = db.getCollection("settings").find(query).limit(size);
        }
        if(size == null){
            search = db.getCollection("settings").find(query);
        }
        
        // If search fails return 500 error
        if(search == null){
            logger.log(new ApiEvent("GET", uriinfo.getPath(), new Date(requestTime), 500, "Unable to query database", "Server was not able to query the database", new Date(System.currentTimeMillis()), null));
            return Response.serverError().entity(new Document("message", "Unable to query database")).build();
        }
        if(search.first() == null){
            //logger.log(new ApiEvent("GET", uriinfo., new Date(requestTime), 404, "Not Found", "No settings found", new Date(System.currentTimeMillis()), null));
            return Response.status(Response.Status.NOT_FOUND).entity(new Document("message", "Settings not found")).build();
        }
        
        // Return query results
        logger.log(new ApiEvent("GET", uriinfo.getPath(), new Date(requestTime), 200, "OK", "Settings found", new Date(System.currentTimeMillis()), null));
        return Response.ok().entity(new Document("test", Util.getRequestUri(uriinfo))).build();
        
    }
    
    /**
     * Creates a new setting and stores it in the DB
     * 
     * @param setting
     * @param securityContext
     * @return 
     * @throws NotFoundException 
     */    
    @Override
    public Response settingsPost(Setting setting, SecurityContext securityContext, UriInfo uriinfo)
    throws NotFoundException {

        long requestTime = System.currentTimeMillis();
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoClient.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        
        MongoDatabase db = mongoClient.getDatabase("barcodes");
        MongoCollection coll = db.getCollection("settings");
        
        // New document to insert on DB
        Document new_setting = new Document();
        // Map setting argument's barcode parameters to a document
        Map map = setting.getBarcode().toMap();
        Document barcode_params = new Document();
        for (Map.Entry entry : ((Set<Map.Entry>) map.entrySet())) {
            barcode_params.append(entry.getKey().toString(), entry.getValue());
        }
        
        // Append key-values to the new document
        new_setting.append("algorithm", setting.getAlgorithm().toString());
        new_setting.append("barcode", barcode_params);
        
        DateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        new_setting.append("created_at", date_format.format(date));
        new_setting.append("active", true);
        
        coll.insertOne(new_setting); // insert document
        ObjectId id = new_setting.getObjectId("_id"); // get inserted document ID
        
        EventLogger logger = new EventLogger(mongoClient);
        
        ResponseBuilder resp = null;
        
        if(id.toString() != null){
            CacheControl cc = new CacheControl();
            cc.setMaxAge(300);
            cc.setNoStore(true);
            cc.setPrivate(true);
            URI u;
            try{
                u = new URI(id.toString());
            }catch(Exception e){
                logger.log(new ApiEvent("POST", uriinfo.getPath(), new Date(requestTime), 500, "Server Error", "HATEOAS URI bad syntax", new Date(System.currentTimeMillis()), null));
                u = null;
            }
            Document doc = new_setting;
            List<Document> links = new ArrayList();
            links.add(new Document().append("rel", "self").append("href", "/v1/settings/" + id.toString()));
            links.add(new Document().append("rel", "update").append("href", "/v1/settings/" + id.toString()));
            links.add(new Document().append("rel", "delete").append("href", "/v1/settings/" + id.toString()));
            doc.append("links", links);

            resp = Response.created(u).status(201).cacheControl(cc).entity(doc);

        }else{
            resp = Response.status(400).entity(new Document("message", "The request can not be fulfilled due to bad sintax"));
        }
        
        return resp.build();
        
    }
    
    @Override
    public Response settingsSettingIdDelete(Long settingId, SecurityContext securityContext, UriInfo uriinfo)
    throws NotFoundException {
        
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, Util.getRequestUri(uriinfo))).build();
    }
    
    /**
     * Gets the setting with the requested ID
     * @param settingId
     * @param securityContext
     * @param request
     * @return
     * @throws NotFoundException 
     */
    @Override
    public Response settingsSettingIdGet(String settingId, SecurityContext securityContext, HttpServletRequest request)
    throws NotFoundException {
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoClient.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        
        MongoDatabase db = mongoClient.getDatabase("barcodes");
        MongoCollection coll = db.getCollection("settings");
        
        
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "teste")).build();
    }
    
    @Override
    public Response settingsSettingIdPut(Long settingId, Setting setting, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
}
