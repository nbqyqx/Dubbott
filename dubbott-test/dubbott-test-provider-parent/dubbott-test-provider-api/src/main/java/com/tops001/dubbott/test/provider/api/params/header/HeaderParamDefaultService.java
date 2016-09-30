package com.tops001.dubbott.test.provider.api.params.header;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/headerparam/default")
public interface HeaderParamDefaultService {
	
	@DefaultValue("english")
	@HeaderParam("Accept-Language")
	public void setAcceptLanguageHeaderParam(String acceptLanguageHeaderParam);

	@DefaultValue("MyCustomConstructorHeader")
	@HeaderParam("CustomConstructorHeader")
    public void setCstrHeaderParam(String cstrHeaderParam);
	
    public Response info(String customMethodHeader);

    @DefaultValue("MyAgent")
    @HeaderParam("User-Agent")
    public void setUserAgent(String aUserAgent);

    public String getUserAgent();

    @DefaultValue("MyCustomPropertyHeader")
    @HeaderParam("CustomPropertyHeader")
    public void setCustomPropertyHeader(String customProperty);

    public String getCustomPropertyHeader();

    @GET
    public Response getHeaderParam(@DefaultValue("MyCustomMethodHeader") @HeaderParam("CustomMethodHeader")String c);

    @POST
    @DefaultValue("MyCustomMethodHeader")
    @HeaderParam("CustomMethodHeader")
    public Response postHeaderParam(String c);

    @PUT
    @DefaultValue("MyCustomMethodHeader")
    @HeaderParam("CustomMethodHeader")
    public Response putHeaderParam(String c);

    @DELETE
    @DefaultValue("MyCustomMethodHeader")
    @HeaderParam("CustomMethodHeader")
    public Response deleteHeaderParam(String c);

    /* FIXME: Check if ResponseBuilder header values can be null */
}
