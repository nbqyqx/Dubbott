package com.alibaba.dubbo.demo.user.facade;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/rest")
public class OrderServiceImpl implements OrderService {

    @Override
    @Path("/order/{orderId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getOrder(@PathParam("orderId") long orderId){
        return "test";
    }

}
