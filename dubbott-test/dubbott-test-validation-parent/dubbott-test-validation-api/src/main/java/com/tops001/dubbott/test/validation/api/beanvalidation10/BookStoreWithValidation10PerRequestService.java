package com.tops001.dubbott.test.validation.api.beanvalidation10;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/bookstore/")
public interface BookStoreWithValidation10PerRequestService {

    @GET
    @Path("bookResponse")
    @Valid
    @NotNull
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response getBookResponse(@QueryParam("id") @NotNull String id);
    
}