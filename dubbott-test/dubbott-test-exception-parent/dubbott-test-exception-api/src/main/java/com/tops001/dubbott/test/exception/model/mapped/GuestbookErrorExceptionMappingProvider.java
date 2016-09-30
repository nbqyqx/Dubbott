package com.tops001.dubbott.test.exception.model.mapped;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GuestbookErrorExceptionMappingProvider implements ExceptionMapper<GuestbookError> {

    public Response toResponse(GuestbookError arg0) {
        CommentError error = new CommentError();
        error.setErrorMessage(arg0.getMessage());
        return Response.status(453).entity(error).type(MediaType.APPLICATION_XML_TYPE).build();
    }

}
