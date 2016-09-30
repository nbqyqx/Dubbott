package com.tops001.dubbott.test.provider.api.params.query;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/queryparam/exception")
public interface QueryParamsExceptionService {

    @QueryParam("CustomStringConstructorFieldQuery")
    public void setCustomStringConstructorFieldQuery(ParamStringConstructor customStringConstructorFieldQuery);

    @QueryParam("CustomValueOfFieldQuery")
    public void setCustomValueOfFieldQuery(QueryValueOf customValueOfFieldQuery);

    @QueryParam("CustomStringConstructorPropertyHeader")
    public void setCustomPropertyStringConstructorQuery(ParamStringConstructor param);

    @QueryParam("CustomValueOfPropertyHeader")
    public void setCustomValueOfPropertyHeader(QueryValueOf param);

    @GET
    @Path("primitive")
    public Response getHeaderParam(@QueryParam("CustomNumQuery") int customNumHeader);

    public static class QueryValueOf {
        String header;

        private QueryValueOf(String aHeader, int num) {
            header = aHeader;
        }

        public String getParamValue() {
            return header;
        }

        public static QueryValueOf valueOf(String v) throws Exception {
            if ("throwWeb".equals(v)) {
                throw new WebApplicationException(Response.status(498)
                                .entity("ParamValueOfWebAppEx").build());
            } else if ("throwNull".equals(v)) {
                throw new NullPointerException("ParamValueOf NPE");
            } else if ("throwEx".equals(v)) {
                throw new Exception("ParamValueOf Exception");
            }
            return new QueryValueOf(v, 100);
        }
    }

    @GET
    @Path("fieldstrcstr")
    public Response getFieldStringConstructorHeaderParam();

    @GET
    @Path("fieldvalueof")
    public Response getFieldValueOfHeaderParam();

    @GET
    @Path("propertystrcstr")
    public Response getPropertyStringConstructorHeaderParam();

    @GET
    @Path("propertyvalueof")
    public Response getPropertyValueOfHeaderParam();

}
