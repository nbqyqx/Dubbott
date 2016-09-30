package com.tops001.dubbott.test.exception.model.nullconditions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GuestbookThrowableMapper implements ExceptionMapper<GuestbookThrowable> {

    public Response toResponse(GuestbookThrowable arg0) {
        return Response.serverError().entity("Throwable mapper used").build();
    }

}
