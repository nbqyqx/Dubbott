package com.tops001.dubbott.test.validation.api.entity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/params/multientity")
public interface MultipleEntityParamsService {

    @GET
    public void getMultipleEntity(String s1, String s2);
    
}
