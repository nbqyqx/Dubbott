package com.tops001.dubbott.test.provider.service.params.header;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.params.header.HeaderParamExceptionService;
import com.tops001.dubbott.test.provider.api.params.header.HeaderStringConstructor;

@Service
public class HeaderParamExceptionServiceImpl implements HeaderParamExceptionService {

    public HeaderParamExceptionServiceImpl() {
    	/* do nothing */
    }

    private HeaderStringConstructor customStringConstructorFieldHeader;

    private HeaderValueOf customValueOfFieldHeader;

    public void setCustomValueOfFieldHeader(HeaderValueOf customValueOfFieldHeader) {
		this.customValueOfFieldHeader = customValueOfFieldHeader;
	}

	public void setCustomStringConstructorFieldHeader(HeaderStringConstructor customStringConstructorFieldHeader) {
		this.customStringConstructorFieldHeader = customStringConstructorFieldHeader;
	}

	private HeaderValueOf customPropertyValueOfHeader;

    private HeaderStringConstructor customPropertyStringConstructorHeader;

    public void setCustomValueOfPropertyHeader(HeaderValueOf param) {
        customPropertyValueOfHeader = param;
    }

    public void setCustomConstructorPropertyHeader(HeaderStringConstructor param) {
        customPropertyStringConstructorHeader = param;
    }

    public Response getHeaderParam(int customNumHeader) {
        return Response.ok().header("RespCustomNumHeader", customNumHeader).build();
    }

    public Response getStringConstructorHeaderParam(HeaderStringConstructor customStringHeader) {
        return Response.ok().header("RespCustomStringHeader", customStringHeader.getHeader())
                        .build();
    }

    public Response getValueOfHeaderParam(HeaderValueOf customValueOfHeader) {
        return Response.ok().header("RespCustomValueOfHeader", customValueOfHeader.getHeader())
                        .build();
    }

    public Response getValueOfHeaderParam(List<HeaderValueOf> customValueOfHeader) {
        if (customValueOfHeader.size() != 1) {
            throw new IllegalArgumentException();
        }
        return Response.ok().header("RespCustomListValueOfHeader",
                                    customValueOfHeader.get(0).getHeader()).build();
    }

    public Response getValueOfHeaderParam(Set<HeaderValueOf> customValueOfHeader) {
        if (customValueOfHeader.size() != 1) {
            throw new IllegalArgumentException();
        }
        return Response.ok().header("RespCustomSetValueOfHeader",
                                    new ArrayList<HeaderValueOf>(customValueOfHeader).get(0)
                                                    .getHeader()).build();
    }

    public Response getValueOfHeaderParam(SortedSet<HeaderValueOf> customValueOfHeader) {
        if (customValueOfHeader.size() != 1) {
            throw new IllegalArgumentException();
        }
        return Response.ok().header("RespCustomSortedSetValueOfHeader",
                                    customValueOfHeader.first().getHeader()).build();
    }

    public Response getFieldStringConstructorHeaderParam() {
        return Response.ok().header("RespCustomStringConstructorFieldHeader",
                                    customStringConstructorFieldHeader.getHeader()).build();
    }

    public Response getFieldValueOfHeaderParam() {
        return Response.ok().header("RespCustomValueOfFieldHeader",
                                    customValueOfFieldHeader.getHeader()).build();
    }

    public Response getPropertyStringConstructorHeaderParam() {
        return Response.ok().header("RespCustomStringConstructorPropertyHeader",
                                    customPropertyStringConstructorHeader.getHeader()).build();
    }

    public Response getPropertyValueOfHeaderParam() {
        return Response.ok().header("RespCustomValueOfPropertyHeader",
                                    customPropertyValueOfHeader.getHeader()).build();
    }

}
