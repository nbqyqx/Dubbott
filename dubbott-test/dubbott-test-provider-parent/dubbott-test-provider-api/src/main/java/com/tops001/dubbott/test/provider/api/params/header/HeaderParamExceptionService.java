package com.tops001.dubbott.test.provider.api.params.header;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/headerparam/exception")
public interface HeaderParamExceptionService {

    @HeaderParam("CustomValueOfPropertyHeader")
    public void setCustomValueOfPropertyHeader(HeaderValueOf param);

    @HeaderParam("CustomStringConstructorPropertyHeader")
    public void setCustomConstructorPropertyHeader(HeaderStringConstructor param);
    
    @HeaderParam("CustomValueOfFieldHeader")
    public void setCustomValueOfFieldHeader(HeaderValueOf customValueOfFieldHeader);

    @HeaderParam("CustomStringConstructorFieldHeader")
	public void setCustomStringConstructorFieldHeader(HeaderStringConstructor customStringConstructorFieldHeader);

    @GET
    @Path("primitive")
    public Response getHeaderParam(@HeaderParam("CustomNumHeader") int customNumHeader);

    @GET
    @Path("constructor")
    public Response getStringConstructorHeaderParam(@HeaderParam("CustomStringHeader") HeaderStringConstructor customStringHeader);

    public static class HeaderValueOf implements Comparable<HeaderValueOf> {
        String header;

        public HeaderValueOf(String aHeader) {
        	header = aHeader;
        }
        
        private HeaderValueOf(String aHeader, int num) {
            header = aHeader;
        }

        public String getHeader() {
            return header;
        }

        public static HeaderValueOf valueOf(String v) throws Exception {
            if ("throwWeb".equals(v)) {
                throw new WebApplicationException(Response.status(498)
                                .entity("HeaderValueOfWebAppEx").build());
            } else if ("throwNull".equals(v)) {
                throw new NullPointerException("HeaderValueOf NPE");
            } else if ("throwEx".equals(v)) {
                throw new Exception("HeaderValueOf Exception");
            }
            return new HeaderValueOf(v, 100);
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        @Override
        //Defect 68201: Implenent camparable interface. There is only one useful variable header in the class, so return the compare to result of header
        public int compareTo(HeaderValueOf o) {
            return this.header.compareTo(o.header);
        }
        
        public String toString() {
        	return header;
        }
    }

    @GET
    @Path("valueof")
    public Response getValueOfHeaderParam(@HeaderParam("CustomValueOfHeader") HeaderValueOf customValueOfHeader);

    @GET
    @Path("listvalueof")
    public Response getValueOfHeaderParam(@HeaderParam("CustomListValueOfHeader") List<HeaderValueOf> customValueOfHeader);

    @GET
    @Path("setvalueof")
    public Response getValueOfHeaderParam(@HeaderParam("CustomSetValueOfHeader") Set<HeaderValueOf> customValueOfHeader);
    @GET
    @Path("sortedsetvalueof")
    public Response getValueOfHeaderParam(@HeaderParam("CustomSortedSetValueOfHeader") SortedSet<HeaderValueOf> customValueOfHeader);

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
