package com.tops001.dubbott.test.exception.model.nullconditions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GuestbookThrowExceptionMapper implements ExceptionMapper<GuestbookThrowException> {

    public Response toResponse(GuestbookThrowException arg0) {
        /*
         * throwing exception/error in here should cause a HTTP 500 status to
         * occur
         */

        if (arg0.getMessage().contains("exception")) {
            throw new GuestbookNullException();
        } else {
            throw new Error("error");
        }

        // TODO: throw this inside a subresource locator
    }

}
