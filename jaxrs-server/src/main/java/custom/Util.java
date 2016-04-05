/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import org.bson.Document;

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
    
    /**
     * 
     * Returns the request URI with query parameters [UNUSED]
     * 
     * @param request
     * @return String
     */
    public static String getRequestUri(HttpServletRequest request)
    {
        
        String basePath = request.getRequestURI();
        //String queryParams = "";
        
        /**
         *  Missing query parameters to string implementation 
         */
        //Map query = request.getParameterMap();
        
        return basePath;
        
    }
    
}
