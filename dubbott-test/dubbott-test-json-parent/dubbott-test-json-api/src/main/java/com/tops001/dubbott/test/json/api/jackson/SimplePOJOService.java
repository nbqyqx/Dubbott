package com.tops001.dubbott.test.json.api.jackson;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tops001.dubbott.test.json.model.jackson.Person;

@Path("/person2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SimplePOJOService {

    @GET
    @Path("map")
    public Map<String, Object> getMap();

    @GET
    @Path("list")
    public List<String> getList();

    @GET
    @Path("person")
    public Person getPOJO();

}
