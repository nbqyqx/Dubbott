package com.tops001.dubbott.test.json.api.jacksonignore;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tops001.dubbott.test.json.model.jacksonignore.TestPojo;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public interface TestJacksonIgnoreService {
	
	@GET
	public TestPojo get() throws JsonProcessingException;
	
}