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
package com.tops001.dubbott.demo.user.facade;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.tops001.dubbott.demo.common.ApiInvokeResult;
import com.tops001.dubbott.demo.common.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author nealhu
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(tags = "com.tops001.dubbott.demo.user.facade.UserRestService", description = "User api")
public interface UserRestService {

    /**
     * the request object is just used to test jax-rs injection.
     */
    @GET
    @Path("{id : \\d+}")
    @ApiOperation(value = "获取用户对象", httpMethod = "GET", response = User.class, notes = "")
    User getUser(@PathParam(value = "id") @Min(value=1L, message="User ID must be greater than 1") Long id/*, HttpServletRequest request*/);

    @POST
    @Path("register")
    @ApiOperation(value = "注册用户", httpMethod = "POST", response = ApiInvokeResult.class, notes = "")
    ApiInvokeResult<User> registerUser(User user);
    
    @GET
    @Path("echo/{who}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "echo service", httpMethod = "GET", response = String.class)
    String echoService(@PathParam("who") String who);
    
    @POST
    @Path("beanparam")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    @ApiOperation(value = "echo service", httpMethod = "GET", response = String.class)
    String beanParam(@BeanParam TalkListParam param, @Context HttpServletRequest req, @HeaderParam(value = "ak") String ak);
}
