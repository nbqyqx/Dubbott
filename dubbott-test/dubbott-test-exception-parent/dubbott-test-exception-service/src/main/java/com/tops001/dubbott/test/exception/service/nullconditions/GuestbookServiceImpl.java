package com.tops001.dubbott.test.exception.service.nullconditions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.exception.api.nullconditions.GuestbookService;
import com.tops001.dubbott.test.exception.model.nullconditions.GuestbookException;
import com.tops001.dubbott.test.exception.model.nullconditions.GuestbookNullException;
import com.tops001.dubbott.test.exception.model.nullconditions.GuestbookThrowException;
import com.tops001.dubbott.test.exception.model.nullconditions.GuestbookThrowable;

@Service
public class GuestbookServiceImpl implements GuestbookService {

    public Response exception() {
        throw new WebApplicationException();
    }

    public Response exceptionWithCause() {
        throw new WebApplicationException(new GuestbookException("Threw checked exception"));
    }

    public Response exceptionWithCauseAndStatus() {
        throw new WebApplicationException(new GuestbookException("Threw checked exception"), 499);
    }

    public Response exceptionWithCauseAndResponse() {
        Response resp =
                        Response.status(Status.NOT_ACCEPTABLE).entity("Entity inside response").build();
        throw new WebApplicationException(new GuestbookException("Threw checked exception"), resp);
    }

    public Response exceptionWithCauseAndResponseStatus() {
        throw new WebApplicationException(new GuestbookException("Threw checked exception"),
                                          Response.Status.BAD_REQUEST);
    }

    public Response exceptionMapperReturnNull() {
        throw new GuestbookNullException("Should not see me");
    }

    public Response exceptionMapperThrowsException() throws GuestbookThrowException {
        throw new GuestbookThrowException("Re-throw an exception");
    }

    public Response exceptionMapperThrowsError() throws GuestbookThrowException {
        throw new GuestbookThrowException("Re-throw an error");
    }

    public Response throwableExceptionMapper() throws GuestbookThrowable {
        throw new GuestbookThrowable();
    }

    public Response throwThrowable() throws Throwable {
        throw new Throwable("Throwable was thrown") {
            private static final long serialVersionUID = 1L;
        };
    }
    
}
