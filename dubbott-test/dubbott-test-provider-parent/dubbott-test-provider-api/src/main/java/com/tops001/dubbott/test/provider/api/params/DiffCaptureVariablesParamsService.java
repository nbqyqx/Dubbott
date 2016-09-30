package com.tops001.dubbott.test.provider.api.params;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * To test that JAX-RS runtime can match request to
 * subresource methods with the same regular expression,
 * but different capture variable names. None of the parameters
 * should have null values.
 */
@Path("diffvarnames")
public interface DiffCaptureVariablesParamsService {

    // See https://issues.apache.org/jira/browse/WINK-344 for history
    @GET
    @Path("{id1}")
    public String getMethod(@PathParam("id1") String id);

    @POST
    @Path("{id2}/post")
    public String doSomething(@PathParam("id2") String id);

    @DELETE
    @Path("{id3}")
    public String deleteMethod(@PathParam("id3") String id);
    
}
