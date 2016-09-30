package com.tops001.dubbott.test.exception.api.nullconditions;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.tops001.dubbott.test.exception.model.nullconditions.GuestbookThrowException;
import com.tops001.dubbott.test.exception.model.nullconditions.GuestbookThrowable;

@Path("guestbooknullconditions")
public interface GuestbookService {

    @GET
    @Path("emptywebappexception")
    public Response exception();

    @GET
    @Path("webappexceptionwithcause")
    public Response exceptionWithCause();

    @POST
    @Path("webappexceptionwithcauseandstatus")
    public Response exceptionWithCauseAndStatus();

    @PUT
    @Path("webappexceptionwithcauseandresponse")
    public Response exceptionWithCauseAndResponse();

    @DELETE
    @Path("webappexceptionwithcauseandresponsestatus")
    public Response exceptionWithCauseAndResponseStatus();

    @GET
    @Path("exceptionmappernull")
    public Response exceptionMapperReturnNull();

    @POST
    @Path("exceptionmapperthrowsexception")
    public Response exceptionMapperThrowsException() throws GuestbookThrowException;

    @POST
    @Path("exceptionmapperthrowserror")
    public Response exceptionMapperThrowsError() throws GuestbookThrowException;

    @PUT
    @Path("throwableexceptionmapper")
    public Response throwableExceptionMapper() throws GuestbookThrowable;

    @DELETE
    @Path("throwsthrowable")
    public Response throwThrowable() throws Throwable;
    
}
