/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import org.bson.Document;

/**
 *
 * @author Camilo
 */
class ApiEvent {

    private String method;
    private String path;
    private Date request_timestamp;
    private int response_http_code;
    private String message;
    private String description;
    private Date response_timestamp;
    
    /**
     * Casts event to a document to insert on DB
     * @return Document
     */
    public Document toDocument()
    {
        return new Document();
    }
    
}
