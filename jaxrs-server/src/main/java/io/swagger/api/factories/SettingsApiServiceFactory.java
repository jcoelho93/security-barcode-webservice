package io.swagger.api.factories;

import io.swagger.api.SettingsApiService;
import io.swagger.api.impl.SettingsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-01T17:42:32.367Z")
public class SettingsApiServiceFactory {

   private final static SettingsApiService service = new SettingsApiServiceImpl();

   public static SettingsApiService getSettingsApi()
   {
      return service;
   }
}
