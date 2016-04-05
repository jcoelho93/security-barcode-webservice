/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Camilo
 */
public class Util {
    
       
    /**
     * 
     * Exception Stack Trace to String
     * 
     * @param Exception e
     * @return stack trace as a string
     */
    public static String stackTraceToString(Exception e)
    {
        
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        
        return writer.toString();
        
    }
    
    public static String getRequestUri(UriInfo uri_info)
    {
        
        String basePath = uri_info.getAbsolutePath().getPath();
        String queryParams = "";
        
        MultivaluedMap mvm = uri_info.getQueryParameters();
        
        Iterator<String> i = mvm.keySet().iterator();
        
        while(i.hasNext()){
            String key = (String)(i.next());
            queryParams += key + "=" + mvm.getFirst(key) + "&";
        }
        
        return basePath + "?";
        
    }
    
}
