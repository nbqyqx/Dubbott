package com.tops001.dubbott.test.provider.api.params;

import javax.ws.rs.CookieParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("cookiemonster")
public interface CookieParamService {

    @PUT
    @Produces("text/plain")
    public Response swipe(@CookieParam("jar") @DefaultValue("0") String jarSwipes);
    
}
