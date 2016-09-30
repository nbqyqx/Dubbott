package com.tops001.dubbott.test.consumer.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("greet")
@Produces(MediaType.TEXT_PLAIN)
public interface GreetService {

	@Path("{username}")
	@GET
	String welcome(@PathParam("username")String username);
	
}
