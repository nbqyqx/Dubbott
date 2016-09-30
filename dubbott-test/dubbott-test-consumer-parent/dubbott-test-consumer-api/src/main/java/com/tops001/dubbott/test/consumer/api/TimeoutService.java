package com.tops001.dubbott.test.consumer.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("timeout")
public interface TimeoutService {

    @GET
    public String timeout(@QueryParam("timeout") long timeout);
    
}
