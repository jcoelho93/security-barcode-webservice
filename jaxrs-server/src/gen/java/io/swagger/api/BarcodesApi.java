package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.BarcodesApiService;
import io.swagger.api.factories.BarcodesApiServiceFactory;

import io.swagger.annotations.ApiParam;

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

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/barcodes")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the barcodes API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-01T17:42:32.367Z")
public class BarcodesApi  {
   private final BarcodesApiService delegate = BarcodesApiServiceFactory.getBarcodesApi();

    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Gets `Barcode` resources. **Optional** query parameters:\n  \n* **size** determines size of returned array\n* **date** determines the date the barcode was created\n* **algorithm** determines the encryption algorithm used\n* **barcode** determines the type of barcode", response = Barcode.class, responseContainer = "List", tags={ "Barcodes",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = Barcode.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "No barcodes found", response = Barcode.class, responseContainer = "List") })

    public Response barcodesGet(@ApiParam(value = "Size of returned array") @QueryParam("size") Integer size,@ApiParam(value = "Date of returned barcodes") @QueryParam("date") String date,@ApiParam(value = "Encryption algorithm used", allowableValues="sha-256, aes, rsa", defaultValue="sha-256") @DefaultValue("sha-256") @QueryParam("algorithm") String algorithm,@ApiParam(value = "Type of barcode", allowableValues="qr_code, data_matrix, pdf_417", defaultValue="qr_code") @DefaultValue("qr_code") @QueryParam("barcode") String barcode,@Context SecurityContext securityContext,@Context HttpServletRequest request)
    throws NotFoundException {
        return delegate.barcodesGet(size,date,algorithm,barcode,securityContext,request);
    }
    @DELETE
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Deletes a barcode.", response = void.class, tags={ "Barcodes",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Barcode deleted", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Barcode not found", response = void.class) })

    public Response barcodesIdDelete(@ApiParam(value = "The `Barcode` identifier number",required=true) @PathParam("id") String id,@Context SecurityContext securityContext, @Context HttpServletRequest request)
    throws NotFoundException {
        return delegate.barcodesIdDelete(id,securityContext,request);
    }
    @GET
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Gets the barcode.", response = Barcode.class, tags={ "Barcodes",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Barcode found", response = Barcode.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Barcode not found", response = Barcode.class) })

    public Response barcodesIdGet(@ApiParam(value = "The `Barcode` identifier number",required=true) @PathParam("id") String id,@Context SecurityContext securityContext, @Context HttpServletRequest request)
    throws NotFoundException {
        return delegate.barcodesIdGet(id,securityContext,request);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Creates a new `Barcode` resource.", response = Barcode.class, tags={ "Barcodes" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Barcode created", response = Barcode.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "The request can not be fullfilled due to bad sintax", response = Barcode.class) })

    public Response barcodesPost(@ApiParam(value = "Data to be stored on barcode" ,required=true) InputData data,@Context SecurityContext securityContext, @Context HttpServletRequest request)
    throws NotFoundException {
        return delegate.barcodesPost(data,securityContext,request);
    }
}
