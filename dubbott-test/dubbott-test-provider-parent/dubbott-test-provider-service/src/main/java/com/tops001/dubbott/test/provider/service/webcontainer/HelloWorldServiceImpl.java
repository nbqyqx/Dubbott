package com.tops001.dubbott.test.provider.service.webcontainer;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.webcontainer.HelloWorldService;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {

	@Override
	public String getMessage() {
		// Note that if null is returned from a resource method, a HTTP 204 (No
        // Content) status code response is sent.
        return HelloWorldServiceImpl.message;
	}

	@Override
	public String postMessage(String incomingMessage) {
		// A plain Java parameter is used to represent the request body. The
        // JAX-RS runtime will map the request body to a String.
		HelloWorldServiceImpl.message = incomingMessage;
        return incomingMessage;
	}

	@Override
	public Response putMessage(byte[] incomingMessage) {
		// Note that different Java types can be used to map the
        // incoming request body to a Java type.
		HelloWorldServiceImpl.message = new String(incomingMessage);

        // Note that a javax.ws.rs.core.Response object is returned. A Response
        // object can be built which contains additional HTTP headers, a status
        // code, and the entity body.
        return Response.ok(incomingMessage).type(MediaType.TEXT_PLAIN).build();
	}

	@Override
	public Response deleteMessage() {
		HelloWorldServiceImpl.message = null;
        // Note that a javax.ws.rs.core.Response object is returned. In this
        // method a HTTP 204 status code (No Content) is returned.
        return Response.noContent().build();
	}
	
	/**
     * A static variable to hold a message. Note that for this sample, the field
     * is static because a new <code>HelloWorldResource</code> object is created
     * per request.
     */
    private static volatile String message = "Hello World!";

}
