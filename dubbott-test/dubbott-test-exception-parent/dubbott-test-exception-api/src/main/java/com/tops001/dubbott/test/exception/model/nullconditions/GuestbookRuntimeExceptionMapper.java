package com.tops001.dubbott.test.exception.model.nullconditions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GuestbookRuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

    public Response toResponse(RuntimeException arg0) {
        return Response.serverError().entity("RuntimeExceptionMapper was used").build();
    }

}
