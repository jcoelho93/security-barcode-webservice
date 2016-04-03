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
import static io.swagger.util.PrimitiveType.URI;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
    
    @Override
    public Response settingsGet(Integer size, String date, String algorithm, String barcode, SecurityContext securityContext)
    throws NotFoundException {
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("barcodes");
        
        //Defining query
        FindIterable<Document> search = null;
        Document query = new Document();
        if(date != null){
            DateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
            Date new_date = null;
            try{
                new_date = date_format.parse(date);
            }catch(Exception e){
                return Response.status(400).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Cannot parse date. Use dd-MM-yyyy")).build();
            }
            query.append("created_at", date_format.format(new_date));
        }
        if(algorithm != null){
            query.append("algorithm",algorithm);
        }
        
        if(size != null && size > 0){
            search = db.getCollection("settings").find(query).limit(size);
        }
        if(size == null){
            search = db.getCollection("settings").find(query);
        }
        
        if(search == null){
            return Response.serverError().build();
        }
        
        return Response.ok().entity(search).build();
        
    }
    
    /*
    *
    *   Stores a new setting in the database
    *
    */
    
    @Override
    public Response settingsPost(Setting setting, SecurityContext securityContext)
    throws NotFoundException {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoClient.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        
        MongoDatabase db = mongoClient.getDatabase("barcodes");
        MongoCollection coll = db.getCollection("settings");
        
        Document new_setting = new Document();
        Map map = setting.getBarcode().toMap();
        Document barcode_params = new Document();
        for (Map.Entry entry : ((Set<Map.Entry>) map.entrySet())) {
            barcode_params.append(entry.getKey().toString(), entry.getValue());
        }
        
        new_setting.append("algorithm", setting.getAlgorithm().toString());
        new_setting.append("barcode", barcode_params);
        
        DateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        new_setting.append("created_at", date_format.format(date));
        new_setting.append("active", true);
        
        coll.insertOne(new_setting); // insert document
        ObjectId id = new_setting.getObjectId("_id"); // get inserted document ID
        
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
                u = null;
            }
            Document doc = new_setting;
            List<Document> links = new ArrayList();
            links.add(new Document().append("rel", "self").append("href", "/v1/settings/" + id.toString()));
            doc.append("links", links);

            resp = Response.created(u).status(201).cacheControl(cc).entity(doc);
        }else{
            resp = Response.status(400).entity("The request can not be fulfilled due to bad sintax");
        }
        
        return resp.build();
        
    }
    
    @Override
    public Response settingsSettingIdDelete(Long settingId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response settingsSettingIdGet(String settingId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response settingsSettingIdPut(Long settingId, Setting setting, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
}
