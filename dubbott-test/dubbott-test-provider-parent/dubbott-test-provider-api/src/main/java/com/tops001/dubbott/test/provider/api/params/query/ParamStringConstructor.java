package com.tops001.dubbott.test.provider.api.params.query;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ParamStringConstructor {

    String value;

    public ParamStringConstructor(String aValue) throws Exception {
        if ("throwWeb".equals(aValue)) {
            throw new WebApplicationException(Response.status(499).entity("ParamStringConstructor")
                            .build());
        } else if ("throwNull".equals(aValue)) {
            throw new NullPointerException("ParamStringConstructor NPE");
        } else if ("throwEx".equals(aValue)) {
            throw new Exception("ParamStringConstructor Exception");
        }
        value = aValue;
    }

    public String getParamValue() {
        return value;
    }
}
