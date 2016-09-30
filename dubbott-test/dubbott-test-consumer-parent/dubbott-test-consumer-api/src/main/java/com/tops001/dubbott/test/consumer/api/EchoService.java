package com.tops001.dubbott.test.consumer.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Path("/echoaccept")
public interface EchoService {

    @GET
    public Response getAcceptHeaderEcho(@Context HttpHeaders requestHeaders, @Context Request request) throws Exception;
    
}
