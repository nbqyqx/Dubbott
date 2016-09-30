package com.tops001.dubbott.test.provider.api.params.form;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

@Path("/form")
public interface FormParamService {

	@POST
	@Path("withOnlyEntity")
	public String getRes(MultivaluedMap<String, String> entity);

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("withOneKeyAndEntity")
	public String getRes(@FormParam("firstkey") String firstKey, MultivaluedMap<String, String> entity);

	@POST
	@Path("withStringEntity")
	public String getStrEntity(String entity);
}
