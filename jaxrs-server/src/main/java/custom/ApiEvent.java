/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bson.Document;

/**
 *
 * @author Camilo
 */
public class ApiEvent {

    private String method;
    private String path;
    private Date request_timestamp;
    private int response_http_code;
    private String message;
    private String description;
    private Date response_timestamp;
    private String stack;
    
    public ApiEvent()
    {
        this.method = null;
        this.path = null;
        this.request_timestamp = new Date();
        this.response_http_code = 0;
        this.message = null;
        this.description = null;
        this.response_timestamp = new Date();
        this.stack = null;
    }
    
    public ApiEvent(String method, String path, Date request_timestamp, int http_code, String message, String description, Date response_timestamp, String stack)
    {
        
        this.method = method;
        this.path = path;
        this.request_timestamp = request_timestamp;
        this.response_http_code = http_code;
        this.message = message;
        this.description = description;
        this.response_timestamp = response_timestamp;
        this.stack = stack;
        
    }
    
    /**
     * Casts event to a document to insert on DB
     * @return Document
     */    
    public Document toDocument()
    {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Document doc = new Document();
        doc.append("method", this.method);
        doc.append("path", this.path);
        doc.append("request_timestamp", format.format(this.request_timestamp));
        doc.append("http_code", this.response_http_code);
        doc.append("message", this.message);
        doc.append("description", this.description);
        doc.append("response_timestamp", format.format(this.response_timestamp));
        doc.append("stack", this.stack);
        return doc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getRequest_timestamp() {
        return request_timestamp;
    }

    public void setRequest_timestamp(Date request_timestamp) {
        this.request_timestamp = request_timestamp;
    }

    public int getResponse_http_code() {
        return response_http_code;
    }

    public void setResponse_http_code(int response_http_code) {
        this.response_http_code = response_http_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getResponse_timestamp() {
        return response_timestamp;
    }

    public void setResponse_timestamp(Date response_timestamp) {
        this.response_timestamp = response_timestamp;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }
    
    
    
}
