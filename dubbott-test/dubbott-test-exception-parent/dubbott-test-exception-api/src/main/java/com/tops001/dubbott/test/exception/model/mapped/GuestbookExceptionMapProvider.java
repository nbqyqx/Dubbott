package com.tops001.dubbott.test.exception.model.mapped;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GuestbookExceptionMapProvider implements ExceptionMapper<GuestbookException> {

    public Response toResponse(GuestbookException arg0) {
        CommentError error = new CommentError();
        error.setErrorMessage(arg0.getMessage());
        return Response.status(454).entity(error).type("application/xml").build();
    }

}
