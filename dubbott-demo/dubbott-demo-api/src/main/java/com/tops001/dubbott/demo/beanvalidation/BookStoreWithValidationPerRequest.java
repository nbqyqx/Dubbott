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
public interface BookStoreWithValidationPerRequest {
    


    @QueryParam("id")
    public void setId(String id);

    @GET
    @Path("book")
    @Valid
    @NotNull
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public BookWithValidation book();

    @GET
    @Path("bookResponse")
    @Valid
    @NotNull
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response bookResponse();
}