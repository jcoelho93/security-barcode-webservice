/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;

/**
 *
 * @author Camilo
 */
public class EventLogger {

    private MongoClient client;
    
    public EventLogger(MongoClient client)
    {
        
        this.client = client;
        
    }
    
    public EventLogger(String host, int port)
    {
        
        this.client = new MongoClient(host, port);
        
    }
    
    /**
     * Logs an event on the DB
     * @param event
     * @return boolean
     */
    public boolean log(ApiEvent event)
    {
        MongoDatabase db = this.client.getDatabase("barcodes");
        MongoCollection coll = db.getCollection("log");
        
        coll.insertOne(event.toDocument());
        ObjectId id = event.toDocument().getObjectId("_id");
        
        if(id.toString() != null){
            return true;
        }
        
        return false;
        
    }
    
}
