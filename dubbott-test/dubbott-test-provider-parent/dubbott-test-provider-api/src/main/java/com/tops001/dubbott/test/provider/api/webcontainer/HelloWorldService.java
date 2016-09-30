package com.tops001.dubbott.test.provider.api.webcontainer;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * <code>HelloWorldService</code> is a simple POJO which is annotated with
 * JAX-RS annotations to turn it into a JAX-RS resource.
 * <p/>
 * This class has a {@link Path} annotation with the value "helloworld" which
 * means the resource will be available at:
 * <code>http://&lt;hostname&gt;:&lt;port&gt/&lt;context root&gt;/&lt;servlet path&gt;/helloworld</code>
 * <p/>
 * Remember to add this resource class to the {@link HelloWorldApplication#getClasses()} method.
 */
@Path("/helloworld")
public interface HelloWorldService {

    /**
     * Processes a GET request and returns the stored message.
     * 
     * @return the stored message
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage();

    /**
     * Processes a POST request and returns the incoming request message.
     * 
     * @param incomingMessage the request body is mapped to the String by the
     *            JAX-RS runtime using a built-in entity provider
     * @return the original request body
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String postMessage(String incomingMessage);

    /**
     * Processes a PUT request and returns the incoming request message.
     * 
     * @param incomingMessage the request body is mapped to the byte[] by the
     *            JAX-RS runtime using a built-in entity provider
     * @return the original request body in a JAX-RS Response object
     */
    @PUT
    public Response putMessage(byte[] incomingMessage);

    /**
     * Processes a DELETE request.
     * 
     * @return an empty response with a 204 status code
     */
    @DELETE
    public Response deleteMessage();
	
}
