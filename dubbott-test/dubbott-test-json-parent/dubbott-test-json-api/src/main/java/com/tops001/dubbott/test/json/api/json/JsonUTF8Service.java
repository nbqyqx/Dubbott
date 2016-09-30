package com.tops001.dubbott.test.json.api.json;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tops001.dubbott.test.json.model.json.Country;

@Path("/country")
public interface JsonUTF8Service {

    @GET
    @Path("/upper")
    @Produces(MediaType.APPLICATION_JSON)
    public Country[] getCountriesUpperCase();

    @GET
    @Path("/lower")
    @Produces(MediaType.APPLICATION_JSON)
    public Country[] getCountriesLowerCase();
    
}
