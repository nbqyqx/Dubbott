package com.tops001.dubbott.test.exception.model.mapped;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMappingProvider implements ExceptionMapper<RuntimeException> {

    public Response toResponse(RuntimeException arg0) {
        CommentError error = new CommentError();
        error.setErrorMessage(arg0.getMessage());
        return Response.status(450).entity(error).type(MediaType.APPLICATION_XML_TYPE).build();
    }

}
