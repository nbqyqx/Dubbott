package com.tops001.dubbott.test.provider.service.params;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.params.HeaderParamService;

/**
 * Resource with<code>HeaderParam</code>.
 * 
 * @see HeaderParam
 */
@Service
public class HeaderParamServiceImpl implements HeaderParamService {

    private String cstrHeaderParam;

    private String acceptLanguage;

    private String agent;
    
    @Override
	public void setAcceptLanguage(String acceptLanguage) {
		this.acceptLanguage = acceptLanguage;
	}

	@Override
	public void setCstrHeaderParam(String cstrHeaderParam) {
		this.cstrHeaderParam = cstrHeaderParam;
	}

    public Response getHeaderParam(String methodLanguage) {
        return Response.ok("getHeaderParam:" + cstrHeaderParam
                           + ";User-Agent:"
                           + agent
                           + ";Accept-Language:"
                           + acceptLanguage
                           + ";language-method:"
                           + methodLanguage).header("custResponseHeader", "secret").build();
    }

    public Response getHeaderParamPost(HeaderValueOf customHeader, HeaderConstructor customHeader2) {
        return Response.ok().entity("made successful call").build();
    }

    public void setUserAgent(String aUserAgent) {
        agent = aUserAgent;
    }

    public String getUserAgent() {
        return agent;
    }

}
