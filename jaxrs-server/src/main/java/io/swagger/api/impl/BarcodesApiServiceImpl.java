package io.swagger.api.impl;

import barcode.BarcodeGenerator;
import com.google.zxing.WriterException;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.swagger.api.*;
import io.swagger.model.*;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.Barcode;
import java.util.Date;
import io.swagger.model.InputData;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import custom.ApiEvent;
import custom.EventLogger;
import custom.Util;
import encryption.AESEncryptor;
import encryption.Encryptor;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.apache.commons.codec.binary.Base64;
import org.bson.Document;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-01T17:42:32.367Z")
public class BarcodesApiServiceImpl extends BarcodesApiService {
    
    @Override
    public Response barcodesGet(Integer size, String date, String algorithm, String barcode, SecurityContext securityContext, HttpServletRequest request)
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
            query.append("setting.algorithm",algorithm);
        }
        
        //If client defined a barcode type
        if(barcode != null){
            query.append("setting.barcode.type", barcode);
        }
        
        // If client defined an array size
        if(size != null && size > 0){
            search = db.getCollection("barcodes").find(query).limit(size);
        }
        if(size == null){
            search = db.getCollection("barcodes").find(query);
        }
        
        // If search fails return 500 error
        if(search == null){
            logger.log(new ApiEvent("GET", request.getRequestURI(), new Date(requestTime), ApiEvent.SERVER_ERROR, "Unable to query database", "Server was not able to query the database", new Date(System.currentTimeMillis()), null));
            return Response.serverError().entity(new Document("message", "Unable to query database")).build();
        }
        if(search.first() == null){
            logger.log(new ApiEvent("GET", request.getRequestURI(), new Date(requestTime), ApiEvent.NOT_FOUND, "Not Found", "No barcodes found", new Date(System.currentTimeMillis()), null));
            return Response.status(Response.Status.NOT_FOUND).entity(new Document("message", "Barcodes not found")).build();
        }
        
        // Return query results
        logger.log(new ApiEvent("GET", request.getRequestURI(), new Date(requestTime), ApiEvent.OK, "OK", "Barcodes found", new Date(System.currentTimeMillis()), null));
        return Response.ok().entity(search).build();
        
    }
    
    @Override
    public Response barcodesIdDelete(Long id, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response barcodesIdGet(Long id, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response barcodesPost(InputData data, SecurityContext securityContext, HttpServletRequest request)
    throws NotFoundException {
        
        long requestTime = System.currentTimeMillis();
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("barcodes");
        
        EventLogger logger = new EventLogger(mongoClient);
        
        Document settings = data.getSettings().toQuery();
        MongoCollection settingsColl = db.getCollection("settings");
        
        FindIterable available_setting = settingsColl.find(settings).limit(1);
        
        // Checks if the setting specified is available
        if(available_setting.first() == null){
            logger.log(new ApiEvent("POST",request.getRequestURI(),new Date(requestTime),400,"Bad Request", "These settings are not available",new Date(System.currentTimeMillis()),null));
            return Response.status(Response.Status.BAD_REQUEST).entity(new Document("message","These settings are not available")).build();
        }
        

        String algorithm = data.getSettings().getAlgorithm();
        Encryptor encryptor = new Encryptor();
        String encryptedData = null;
        
        if(algorithm.equals("sha-256")){
            try {
                encryptedData = encryptor.hash(data.getData());
                
            } catch (NoSuchAlgorithmException ex) {
                logger.log(new ApiEvent("POST",request.getRequestURI(),new Date(requestTime),500,"Server Error", "No such algorithm exception", new Date(System.currentTimeMillis()),Util.stackTraceToString(ex)));
                return Response.serverError().entity(new Document("stack",Util.stackTraceToString(ex))).build();
            }
        }

        BarcodeGenerator generator = new BarcodeGenerator(data.getSettings());
        BufferedImage imgBuffer = null;
        
        try {
            imgBuffer = generator.generateBarcode(encryptedData);
        } catch (WriterException ex) {
            logger.log(new ApiEvent("POST",request.getRequestURI(),new Date(requestTime),500,"Server Error", "WriterException while generating barcode", new Date(System.currentTimeMillis()),Util.stackTraceToString(ex)));
            return Response.serverError().entity(new Document("message", "Error generating barcode")).build();
        }
        
        String base64 = "";
        
        if(imgBuffer != null){
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] imgBytes = null;
            try {
                ImageIO.write(imgBuffer, "png", output);
                output.flush();
                imgBytes = output.toByteArray();
                output.close();
            } catch (IOException ex) {
                logger.log(new ApiEvent("POST",request.getRequestURI(),new Date(requestTime),500,"Server Error", "IOException while generating barcode", new Date(System.currentTimeMillis()),Util.stackTraceToString(ex)));
                return Response.serverError().entity(new Document("message", "Error generating barcode")).build();
            }
            
            base64 = Base64.encodeBase64String(imgBytes);
            
        }
        
        return Response.ok().entity(new Document("hash",encryptedData).append("base64", base64)).build();
        
    }
    
}
