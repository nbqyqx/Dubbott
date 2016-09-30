package com.tops001.dubbott.test.provider.service.params.query;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.params.query.ParamStringConstructor;
import com.tops001.dubbott.test.provider.api.params.query.QueryParamsExceptionService;

@Service
public class QueryParamsExceptionServiceImpl implements QueryParamsExceptionService {

	public QueryParamsExceptionServiceImpl() {
		/* do nothing */
	}

	private ParamStringConstructor customStringConstructorFieldQuery;

	private QueryValueOf customValueOfFieldQuery;

	private ParamStringConstructor customPropertyStringConstructorQuery;

	private QueryValueOf customPropertyValueOfQuery;

	public void setCustomPropertyStringConstructorQuery(ParamStringConstructor param) {
		customPropertyStringConstructorQuery = param;
	}

	public void setCustomValueOfPropertyHeader(QueryValueOf param) {
		customPropertyValueOfQuery = param;
	}

	public Response getHeaderParam(int customNumHeader) {
		return Response.ok().header("RespCustomNumQuery", customNumHeader).build();
	}

	public Response getFieldStringConstructorHeaderParam() {
		return Response.ok().entity(customStringConstructorFieldQuery.getParamValue()).build();
	}

	public Response getFieldValueOfHeaderParam() {
		return Response.ok().header("RespCustomValueOfFieldHeader", customValueOfFieldQuery.getParamValue()).build();
	}

	public Response getPropertyStringConstructorHeaderParam() {
		return Response.ok().header("RespCustomStringConstructorPropertyQuery",
				customPropertyStringConstructorQuery.getParamValue()).build();
	}

	public Response getPropertyValueOfHeaderParam() {
		return Response.ok().header("RespCustomValueOfPropertyQuery", customPropertyValueOfQuery.getParamValue())
				.build();
	}

	@Override
	public void setCustomStringConstructorFieldQuery(ParamStringConstructor customStringConstructorFieldQuery) {
		this.customPropertyStringConstructorQuery = customStringConstructorFieldQuery;

	}

	@Override
	public void setCustomValueOfFieldQuery(QueryValueOf customValueOfFieldQuery) {
		this.customValueOfFieldQuery = customValueOfFieldQuery;
	}

}
