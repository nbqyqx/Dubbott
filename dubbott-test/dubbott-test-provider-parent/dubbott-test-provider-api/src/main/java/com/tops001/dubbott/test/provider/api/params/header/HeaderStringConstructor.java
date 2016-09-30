package com.tops001.dubbott.test.provider.api.params.header;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class HeaderStringConstructor {
	
    String header;

    public HeaderStringConstructor(String aHeader) throws Exception {
        if ("throwWeb".equals(aHeader)) {
            throw new WebApplicationException(Response.status(499)
                            .entity("HeaderStringConstructorWebAppEx").build());
        } else if ("throwNull".equals(aHeader)) {
            throw new NullPointerException("HeaderStringConstructor NPE");
        } else if ("throwEx".equals(aHeader)) {
            throw new Exception("HeaderStringConstructor Exception");
        }
        header = aHeader;
    }

    public String getHeader() {
        return header;
    }

	@Override
	public String toString() {
		return header;
	}
    
}
