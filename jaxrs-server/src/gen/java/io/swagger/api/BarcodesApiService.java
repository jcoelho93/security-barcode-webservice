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

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-01T17:42:32.367Z")
public abstract class BarcodesApiService {
  
      public abstract Response barcodesGet(Integer size,Date date,String algorithm,String barcode,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response barcodesIdDelete(Long id,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response barcodesIdGet(Long id,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response barcodesPost(InputData data,Long setting,SecurityContext securityContext)
      throws NotFoundException;
  
}