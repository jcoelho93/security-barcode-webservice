package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.SettingsApiService;
import io.swagger.api.factories.SettingsApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.Setting;
import java.util.Date;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/settings")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the settings API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-01T17:42:32.367Z")
public class SettingsApi  {
   private final SettingsApiService delegate = SettingsApiServiceFactory.getSettingsApi();

    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Gets `Setting` objects.\n**Optional** query parameters:\n  \n* **size** determines size of returned array\n* **date** determines the date the resource was created\n* **algorithm** determines the encryption algorithm used\n* **barcode** determines the type of barcode used", response = Setting.class, responseContainer = "List", tags={ "Settings",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "An array of settings", response = Setting.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "No settings found", response = Setting.class, responseContainer = "List") })

    public Response settingsGet(@ApiParam(value = "Size of returned array") @QueryParam("size") Integer size,@ApiParam(value = "Date of returned settings") @QueryParam("date") String date,@ApiParam(value = "Encryption algorithm used", allowableValues="sha-256, aes, rsa") @QueryParam("algorithm") String algorithm,@ApiParam(value = "Type of barcode used", allowableValues="qr_code, data_matrix, pdf_417") @QueryParam("barcode") String barcode,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.settingsGet(size,date,algorithm,barcode,securityContext);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Creates a new `Setting` object.", response = Setting.class, tags={ "Settings",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Setting created", response = Setting.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "The request can not be fullfilled due to bad sintax", response = Setting.class) })

    public Response settingsPost(@ApiParam(value = "The `Setting` object model" ) Setting setting,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.settingsPost(setting,securityContext);
    }
    @DELETE
    @Path("/{setting-id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Deletes this setting", response = void.class, tags={ "Settings",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Setting deleted", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "No such setting", response = void.class) })

    public Response settingsSettingIdDelete(@ApiParam(value = "The `Setting` identifier number",required=true) @PathParam("setting-id") Long settingId,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.settingsSettingIdDelete(settingId,securityContext);
    }
    @GET
    @Path("/{setting-id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Gets information about a `Setting`.", response = Setting.class, tags={ "Settings",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = Setting.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "No such setting", response = Setting.class) })

    public Response settingsSettingIdGet(@ApiParam(value = "The `Setting` identifier number",required=true) @PathParam("setting-id") String settingId,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.settingsSettingIdGet(settingId,securityContext);
    }
    @PUT
    @Path("/{setting-id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Replace a `Setting` resource", response = Setting.class, tags={ "Settings" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Updated successfully", response = Setting.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "No such setting", response = Setting.class) })

    public Response settingsSettingIdPut(@ApiParam(value = "The `Setting` identifier number",required=true) @PathParam("setting-id") Long settingId,@ApiParam(value = "The `Setting` object model" ) Setting setting,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.settingsSettingIdPut(settingId,setting,securityContext);
    }
}
