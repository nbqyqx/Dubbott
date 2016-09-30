package com.tops001.dubbott.test.provider.api.params.newcookie;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("cookiestests")
public interface CookieService {

    @Context
    public void setUriInfo(UriInfo uriInfo);

    @GET
    @Produces("text/plain")
    @Path("getAll")
    public Response getCookie(@Context HttpHeaders headers);

    @GET
    @Produces("text/plain")
    @Path("getValue2")
    public Response getValue2();

    @GET
    @Produces("text/plain")
    @Path("getStaticValue")
    public Response getStaticValue();

    @GET
    @Produces("text/plain")
    @Path("getValue3")
    public Response getValue3();

    @PUT
    @Produces("text/plain")
    public Response setCookies();

    @CookieParam("name3")
    public void setValue3(String value3);
}
