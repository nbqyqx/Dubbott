package com.tops001.dubbott.test.exception.model.mapped;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.impl.WebApplicationExceptionMapper;

@Provider
public class WebApplicationExceptionMapProvider extends WebApplicationExceptionMapper {

    @Context
    private UriInfo uri;

    public Response toResponse(WebApplicationException arg0) {

    	int oldStatus = arg0.getResponse().getStatus();
        Response.ResponseBuilder builder =
                        Response.fromResponse(arg0.getResponse()).header("ExceptionPage",
                                                                         uri.getAbsolutePath().toASCIIString());

        if (oldStatus == 499) {
            builder.status(497);
        } else if (oldStatus == Response.Status.BAD_REQUEST.getStatusCode()) {
            System.out.println("SETTING 496");
            builder.status(496);
        } else if (oldStatus == 481) {
            builder.status(491);
            CommentError error = new CommentError();
            error.setErrorMessage("WebApplicationExceptionMapProvider set message");
            builder.entity(error).type(MediaType.APPLICATION_XML_TYPE);
        }

        return builder.build();
    }

}
