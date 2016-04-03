package io.swagger.api;

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

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-01T17:42:32.367Z")
public abstract class SettingsApiService {
  
      public abstract Response settingsGet(Integer size,String date,String algorithm,String barcode,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response settingsPost(Setting setting,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response settingsSettingIdDelete(Long settingId,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response settingsSettingIdGet(String settingId,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response settingsSettingIdPut(Long settingId,Setting setting,SecurityContext securityContext)
      throws NotFoundException;
  
}
