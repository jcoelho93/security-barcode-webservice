package io.swagger.api;

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
import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-01T17:42:32.367Z")
public abstract class BarcodesApiService {
  
      public abstract Response barcodesGet(Integer size,String date,String algorithm,String barcode,SecurityContext securityContext, HttpServletRequest request)
      throws NotFoundException;
  
      public abstract Response barcodesIdDelete(String id,SecurityContext securityContext, HttpServletRequest request)
      throws NotFoundException;
  
      public abstract Response barcodesIdGet(String id,SecurityContext securityContext,HttpServletRequest request)
      throws NotFoundException;
  
      public abstract Response barcodesPost(InputData data,SecurityContext securityContext, HttpServletRequest request)
      throws NotFoundException;
  
}
