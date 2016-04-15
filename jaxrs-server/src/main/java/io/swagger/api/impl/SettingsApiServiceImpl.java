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
import com.mongodb.client.result.UpdateResult;
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
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.CacheControl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;
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
     * @param request
     * @return
     * @throws NotFoundException 
     */
    @Override
    public Response settingsGet(Integer size, String date, String algorithm, String barcode, SecurityContext securityContext, HttpServletRequest request)
    throws NotFoundException {
        
        long requestTime = System.currentTimeMillis();
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("barcodes");
        
        //Defining query
        FindIterable<Document> search = null;
        Document query = new Document();
        query.append("active", true);
        
        EventLogger logger = new EventLogger(mongoClient);
        
        // If client defined a date
        if(date != null){
            DateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
            Date new_date;
            try{
                new_date = date_format.parse(date);
            }catch(Exception e){
                logger.log(new ApiEvent("GET", request.getRequestURI(), new Date(requestTime), ApiEvent.BAD_REQUEST, "Bad request", "Cannot parse date", new Date(System.currentTimeMillis()), Util.stackTraceToString(e)));
                return Response.status(400).entity(new Document("message", "Cannot parse date. Use dd-MM-yyyy")).build();
            }
            query.append("created_at", date_format.format(new_date));
        }
        
        // If client defined an algorithm
        if(algorithm != null){
            query.append("algorithm",algorithm);
        }
        
        //If client defined a barcode type
        if(barcode != null){
            query.append("barcode.type", barcode);
        }
        
        // If client defined an array size
        if(size != null && size > 0){
            search = db.getCollection("settings").find(query).limit(size).sort(new Document("created_at",-1));
        }
        if(size == null){
            search = db.getCollection("settings").find(query).sort(new Document("created_at",-1));
        }
        
        // If search fails return 500 error
        if(search == null){
            logger.log(new ApiEvent("GET", request.getRequestURI(), new Date(requestTime), ApiEvent.SERVER_ERROR, "Unable to query database", "Server was not able to query the database", new Date(System.currentTimeMillis()), null));
            return Response.serverError().entity(new Document("message", "Unable to query database")).build();
        }
        if(search.first() == null){
            logger.log(new ApiEvent("GET", request.getRequestURI(), new Date(requestTime), ApiEvent.NOT_FOUND, "Not Found", "No settings found", new Date(System.currentTimeMillis()), null));
            return Response.status(Response.Status.NOT_FOUND).entity(new Document("message", "Settings not found")).build();
        }
        
        // Return query results
        logger.log(new ApiEvent("GET", request.getRequestURI(), new Date(requestTime), ApiEvent.OK, "OK", "Settings found", new Date(System.currentTimeMillis()), null));
        return Response.ok().entity(search).build();
        
    }
    
    /**
     * Creates a new setting and stores it in the DB
     * 
     * @param setting
     * @param securityContext
     * @param request
     * @return 
     * @throws NotFoundException 
     */    
    @Override
    public Response settingsPost(Setting setting, SecurityContext securityContext, HttpServletRequest request)
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
            if(entry.getValue() != null){
                barcode_params.append(entry.getKey().toString(), entry.getValue());
            }
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
        
        ResponseBuilder resp;
        
        if(id.toString() != null){
            CacheControl cc = new CacheControl();
            cc.setMaxAge(300);
            cc.setNoStore(true);
            cc.setPrivate(true);
            URI u;
            try{
                u = new URI(id.toString());
            }catch(Exception e){
                logger.log(new ApiEvent("POST", request.getRequestURI(), new Date(requestTime), ApiEvent.SERVER_ERROR, "Server Error", "HATEOAS URI bad syntax", new Date(System.currentTimeMillis()), null));
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
        
        logger.log(new ApiEvent("POST", request.getRequestURI(), new Date(requestTime), ApiEvent.CREATED, "Created", "New setting created", new Date(System.currentTimeMillis()), null));
        return resp.build();
        
    }
    
    /**
     * 
     * Disables the selected setting
     * 
     * @param settingId
     * @param securityContext
     * @param request
     * @return
     * @throws NotFoundException 
     */
    @Override
    public Response settingsSettingIdDelete(String settingId, SecurityContext securityContext, HttpServletRequest request)
    throws NotFoundException {
        
        long requestTime = System.currentTimeMillis();
        
        ObjectId id = new ObjectId(settingId);
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoClient.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        
        MongoDatabase db = mongoClient.getDatabase("barcodes");
        MongoCollection coll = db.getCollection("settings");
        
        EventLogger logger = new EventLogger(mongoClient);
        
        UpdateResult result;
        result = coll.updateOne(new Document("_id",id), new Document("$set", new Document("active", false)));
        
        if(result.getModifiedCount() == 1){
            logger.log(new ApiEvent("DELETE", request.getRequestURI(), new Date(requestTime), ApiEvent.OK, "OK", "Setting was disabled", new Date(System.currentTimeMillis()), null));
            Document entity = new Document();
            entity.append("put", request.getRequestURI());
            entity.append("settings", "/v1/settings");
            return Response.ok().entity(entity).build();
        }else{
            logger.log(new ApiEvent("DELETE", request.getRequestURI(), new Date(requestTime), ApiEvent.NOT_FOUND, "Not found", "No setting was modified", new Date(System.currentTimeMillis()), null));
            return Response.status(404).entity(new Document("settings", "/v1/settings")).build();
        }
        
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
        
        long requestTime = System.currentTimeMillis();
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoClient.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        
        MongoDatabase db = mongoClient.getDatabase("barcodes");
        MongoCollection coll = db.getCollection("settings");
        
        ObjectId objId = new ObjectId(settingId);
        
        Document query = new Document("_id", objId);
        
        FindIterable search = coll.find(query).limit(1);
        
        Document setting = (Document)search.first();
        
        List<Document> links = new ArrayList();
        links.add(new Document().append("rel", "self").append("href", "/v1/settings/" + setting.get("_id")));
        links.add(new Document().append("rel", "put").append("href", "/v1/settings/" + setting.get("_id")));
        links.add(new Document().append("rel", "delete").append("href", "/v1/settings/" + setting.get("_id")));
        
        setting.append("links",links);
        
        EventLogger logger = new EventLogger(mongoClient);
        logger.log(new ApiEvent("GET", request.getRequestURI(), new Date(requestTime), ApiEvent.OK, "OK", "Setting found", new Date(System.currentTimeMillis()), null));
        
        return Response.ok().entity(setting).build();
    }
    
    /**
     * 
     * Updates the selected setting
     * 
     * @param settingId
     * @param setting
     * @param securityContext
     * @param request
     * @return
     * @throws NotFoundException 
     */
    @Override
    public Response settingsSettingIdPut(String settingId, Setting setting, SecurityContext securityContext, HttpServletRequest request)
    throws NotFoundException {
        
        long requestTime = System.currentTimeMillis();
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoClient.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        
        MongoDatabase db = mongoClient.getDatabase("barcodes");
        MongoCollection coll = db.getCollection("settings");
        
        EventLogger logger = new EventLogger(mongoClient);
        
        Document query = new Document("_id", new ObjectId(settingId));
        
        Document update = new Document();
        //If client wants to change the algorithm
        if(setting.getAlgorithm() != null){
            update.append("algorithm", setting.getAlgorithm().toString());
        }
        // If client wants to disable the setting
        if(setting.isActive()){
            update.append("active", true);
        }else{
            logger.log(new ApiEvent("PUT", request.getRequestURI(), new Date(requestTime),404,"Method not allowed", "Use DELETE instead to disable setting",new Date(System.currentTimeMillis()),null));
            return Response.status(404).entity(new Document("message","Use DELETE instead to disable setting")).build();
        }
        
        // If client wants to change the barcode parameters
        if(setting.getBarcode() != null){
            
            Document barcodeParams = new Document();
            Map map = setting.getBarcode().toMap();
            for (Map.Entry entry : ((Set<Map.Entry>) map.entrySet())) {
                barcodeParams.append(entry.getKey().toString(), entry.getValue());
            }
            
            update.append("barcode", barcodeParams);
            
        }        
                
        UpdateResult result;
        result = coll.updateOne(query, new Document("$set", update));
        
        if(result.getModifiedCount() == 0){
            logger.log(new ApiEvent("PUT", request.getRequestURI(), new Date(requestTime),404, "Not found", "No setting updated", new Date(System.currentTimeMillis()),null));
            return Response.status(Response.Status.NOT_FOUND).entity(new Document("message","No setting updated")).build();
        }
        
        FindIterable updated = coll.find(new Document("_id", new ObjectId(settingId)));
        
        Document updated_setting = (Document)updated.first();
        
        // HATEOAS Links
        List<Document> links = new ArrayList();
        String id = updated_setting.get("_id").toString();
        
        links.add(new Document().append("rel", "self").append("href", "/v1/settings/" + id));
        links.add(new Document().append("rel", "put").append("href", "/v1/settings/" + id));
        links.add(new Document().append("rel", "delete").append("href", "/v1/settings/" + id));
        
        updated_setting.append("links", links);
        
        logger.log(new ApiEvent("GET", request.getRequestURI(), new Date(requestTime), ApiEvent.OK, "OK", "Setting updated", new Date(System.currentTimeMillis()), null));
        return Response.ok().entity(updated_setting).build();
    }
    
}
