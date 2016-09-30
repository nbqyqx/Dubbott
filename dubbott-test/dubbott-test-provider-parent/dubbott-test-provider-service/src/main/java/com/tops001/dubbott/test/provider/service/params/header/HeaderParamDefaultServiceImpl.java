package com.tops001.dubbott.test.provider.service.params.header;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.params.header.HeaderParamDefaultService;

@Service
public class HeaderParamDefaultServiceImpl implements HeaderParamDefaultService {

	private String customConstructorHeaderParam;

	private String customPropertyHeaderParam;

	private String agent;
	
	private String acceptLanguageHeaderParam;

	@Override
	public void setAcceptLanguageHeaderParam(String acceptLanguageHeaderParam) {
		this.acceptLanguageHeaderParam = acceptLanguageHeaderParam;
	}
	
	@Override
	public void setCstrHeaderParam(String cstrHeaderParam) {
		this.customConstructorHeaderParam = cstrHeaderParam;
	}
	
	public Response info(String customMethodHeader) {
		Response r = Response.status(Status.OK).header("RespCustomConstructorHeader", customConstructorHeaderParam)
				.header("RespAccept-Language", acceptLanguageHeaderParam)
				.header("RespCustomMethodHeader", customMethodHeader)
				.header("RespUserAgent", agent)
				.header("RespCustomPropertyHeader", customPropertyHeaderParam).build();
		return r;
	}

	public void setUserAgent(String aUserAgent) {
		agent = aUserAgent;
	}

	public String getUserAgent() {
		return agent;
	}

	public void setCustomPropertyHeader(String customProperty) {
		customPropertyHeaderParam = customProperty;
	}

	public String getCustomPropertyHeader() {
		return customPropertyHeaderParam;
	}

	public Response getHeaderParam(String c) {
		return info(c);
	}

	public Response postHeaderParam(String c) {
		return info(c);
	}

	public Response putHeaderParam(String c) {
		return info(c);
	}

	public Response deleteHeaderParam(String c) {
		return info(c);
	}

}
