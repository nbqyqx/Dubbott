/*******************************************************************************
 * Copyright (c) 2016 Tops Tech Corp.
 *
 *
 * This software is the confidential and proprietary information of
 * Tops Tech Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with tops001.com. 
 * *******************************************************************************/
package com.tops001.dubbott.demo.beanvalidation;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/bookstore/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(tags = "com.tops001.dubbott.demo.beanvalidation.BookStoreWithValidation", description = "User api")
public interface BookStoreWithValidation   {   

    @GET
    @Path("/books/{bookId}")
    @Valid
    @NotNull
    public BookWithValidation getBook(@Pattern(regexp = "\\d+")  @PathParam("bookId") String id);
   
    @GET
    @Path("/booksResponse/{bookId}")
    @Valid
    @NotNull
    public Response getBookResponse(@PathParam("bookId") String id);

    @GET
    @Path("/booksResponseNoValidation/{bookId}")
    public Response getBookResponseNoValidation(@PathParam("bookId") String id);

    @POST
    @Path("/books")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @ApiOperation(value = "添加book", httpMethod = "POST", response = Response.class, notes = "" )
    public Response addBook(@Context final UriInfo uriInfo,
                            @NotNull @Size(min = 1, max = 50) @FormParam("id") String id,
                            @FormParam("name") String name);

    @POST
    @Path("/books/direct")
    //@Consumes("text/xml")
    public Response addBookDirect(@Valid BookWithValidation book, @Context final UriInfo uriInfo);

    @POST
    @Path("/books/directmany")
    public Response addBooksDirect(@Valid List<BookWithValidation> list, @Context final UriInfo uriInfo);

    @Valid
    @GET
    @Path("/books")
    public Collection<BookWithValidation> list(@DefaultValue("1") @Min(1) @QueryParam("page") int page);
    
    
    @DELETE
    @Path("/books")
    public Response clear();
}
