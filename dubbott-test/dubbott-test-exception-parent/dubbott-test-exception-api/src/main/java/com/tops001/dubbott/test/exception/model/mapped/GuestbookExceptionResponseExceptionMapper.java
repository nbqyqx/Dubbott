package com.tops001.dubbott.test.exception.model.mapped;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.ResponseExceptionMapper;

public class GuestbookExceptionResponseExceptionMapper implements ResponseExceptionMapper<GuestbookException> {

	@Override
	public GuestbookException fromResponse(Response r) {
		CommentError c = r.readEntity(CommentError.class);
		String errorMsg = c.getErrorMessage();
		return new GuestbookException(errorMsg);
	}

}
