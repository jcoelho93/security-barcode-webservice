package io.swagger.api.factories;

import io.swagger.api.BarcodesApiService;
import io.swagger.api.impl.BarcodesApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-01T17:42:32.367Z")
public class BarcodesApiServiceFactory {

   private final static BarcodesApiService service = new BarcodesApiServiceImpl();

   public static BarcodesApiService getBarcodesApi()
   {
      return service;
   }
}
