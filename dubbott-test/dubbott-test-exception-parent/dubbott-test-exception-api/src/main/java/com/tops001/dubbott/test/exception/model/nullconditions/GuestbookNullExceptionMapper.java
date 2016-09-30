package com.tops001.dubbott.test.exception.model.nullconditions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GuestbookNullExceptionMapper implements ExceptionMapper<GuestbookNullException> {

    public Response toResponse(GuestbookNullException arg0) {
        /* intentionally return null */
        return null;
    }

}
