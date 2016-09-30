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
package com.tops001.dubbott.demo.consumer;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author nealhu
 */
@Path("/consumer")
@Api(tags = "com.tops001.dubbott.demo.consumer.UserRestConsumerService", description = "User api consumer")
public interface UserRestConsumerService {

    @GET
    @Path("echo/{who}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "echo service consumer", httpMethod = "GET", response = String.class)
    String echoService(@PathParam("who") String who);
    
    @GET
    @Path("api/echo/{who}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "jaxrs api echo service consumer", httpMethod = "GET", response = String.class)
    String apiEchoService(@PathParam("who") String who);
}
