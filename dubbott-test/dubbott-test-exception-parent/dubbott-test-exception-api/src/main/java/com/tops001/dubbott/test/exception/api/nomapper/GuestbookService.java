package com.tops001.dubbott.test.exception.api.nomapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.tops001.dubbott.test.exception.model.nomapper.Comment;
import com.tops001.dubbott.test.exception.model.nomapper.CommentError;
import com.tops001.dubbott.test.exception.model.nomapper.GuestbookException;

/**
 * The main JAX-RS resource.
 */
@Path("guestbooknomap")
public interface GuestbookService {

    public static class MyWebAppException extends WebApplicationException {

        private static final long serialVersionUID = -2022185988670037226L;

        final private Response resp;

        public MyWebAppException(int status) {
            CommentError error = new CommentError();
            error.setErrorMessage("Cannot post an invalid message.");
            resp = Response.status(status).type("text/xml").entity(error).build();
        }

        @Override
        public Response getResponse() {
            return resp;
        }
    }

    /**
     * Adds a new message to the database.
     * 
     * @return HTTP status 200
     */
    @POST
    @Consumes({ "text/xml" })
    @Produces({ "text/xml" })
    public Response createMessage(Comment aMessage, @Context UriInfo uriInfo);

    @PUT
    @Path("{id}")
    public Response updateMessage(Comment aMessage, @PathParam("id") String msgId)
                    throws GuestbookException;

    @GET
    @Path("/{id}")
    @Produces({ "text/xml" })
    public Response readMessage(@PathParam("id") String msgId);

    @DELETE
    @Path("{id}")
    public Response deleteMessage(@PathParam("id") String msgId);

    @POST
    @Path("/clear")
    public Response clearMessages();
    
}
