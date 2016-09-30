package com.tops001.dubbott.test.provider.api.webcontainer;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("environment/webcontainer/context/")
public interface WebContainerContextInjectionService {

    @GET
    public String fetchHTTPRequestPathInfo(@Context HttpServletRequest httpServletRequest);
    
    @POST
    public String fetchHTTPResponse(@Context HttpServletResponse httpServletResponse);

    @GET
    @Path("servletcontext")
    public void fetchServletContext(@Context HttpServletRequest httpServletRequest,
			@Context HttpServletResponse httpServletResponse, @Context ServletContext servletContext) throws IOException, ServletException;

    @GET
    @Path("servletconfig")
    public String fetchServletConfig(@Context ServletConfig servletConfig);
	
}
