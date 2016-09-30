package com.tops001.dubbott.test.provider.api.params;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * A simple resource to test <code>@QueryParam</code>.
 */
@Path("query")
public interface QueryParamService {

    /**
     * setAQueryId with simple query parameter.
     * 
     * @param aQueryID a query parameter
     */
    public void setAQueryId(@DefaultValue("notset") @QueryParam("queryid") String aQueryID);

    /**
     * GET method on root resource.
     * 
     * @return modified string with constructor query parameter
     */
    @GET
    public String getConstructorQueryID();

    /**
     * PUT method on root resource.
     * 
     * @return modified string with constructor query parameter
     */
    @PUT
    public String putConstructorQueryID();

    /**
     * POST method on root resource.
     * 
     * @return modified string with constructor query parameter
     */
    @POST
    public String postConstructorQueryID();

    /**
     * DELETE method on root resource.
     * 
     * @return modified string with constructor query parameter
     */
    @DELETE
    public String deleteConstructorQueryID();

    /**
     * GET method with different path and additional query parameter.
     * 
     * @param aSimpleParameter an additional simple parameter
     * @return modified string with constructor and path query parameters
     */
    @GET
    @Path("simple")
    public String getSimpleQueryParameter(@QueryParam("simpleParam") String aSimpleParameter);

    /**
     * PUT method with different path and additional query parameter.
     * 
     * @param aSimpleParameter an additional simple parameter
     * @return modified string with constructor and path query parameters
     */
    @DELETE
    @Path("simple")
    public String deleteSimpleQueryParameter(@QueryParam("simpleParam") String aSimpleParameter);

    /**
     * POST method with different path and additional query parameter.
     * 
     * @param aSimpleParameter an additional simple parameter
     * @return modified string with constructor and path query parameters
     */
    @PUT
    @Path("simple")
    public String putSimpleQueryParameter(@QueryParam("simpleParam") String aSimpleParameter);

    /**
     * GET method with different path and additional query parameter.
     * 
     * @param aSimpleParameter an additional simple parameter
     * @return modified string with constructor and path query parameters
     */
    @POST
    @Path("simple")
    public String postSimpleQueryParameter(@QueryParam("simpleParam") String aSimpleParameter);

    /**
     * GET method with multiple query parameters.
     * 
     * @param multiparam1
     * @param param123
     * @param oneMoreParam
     * @return modified string with constructor and all path query parameters
     */
    @GET
    @Path("multiple")
    public String getMultiQueryParameter(@QueryParam("multiParam1") String multiparam1,
                                         @QueryParam("123Param") String param123,
                                         @QueryParam("1MOREParam") String oneMoreParam);

    /**
     * DELETE method with multiple query parameters.
     * 
     * @param multiparam1
     * @param param123
     * @param oneMoreParam
     * @return modified string with constructor and all path query parameters
     */
    @DELETE
    @Path("multiple")
    public String deleteMultiQueryParameter(@QueryParam("multiParam1") String multiparam1,
                                            @QueryParam("123Param") String param123,
                                            @QueryParam("1MOREParam") String oneMoreParam);

    /**
     * POST method with multiple query parameters.
     * 
     * @param multiparam1
     * @param param123
     * @param oneMoreParam
     * @return modified string with constructor and all path query parameters
     */
    @POST
    @Path("multiple")
    public String postMultiQueryParameter(@QueryParam("multiParam1") String multiparam1,
                                          @QueryParam("123Param") String param123,
                                          @QueryParam("1MOREParam") String oneMoreParam);

    /**
     * PUT method with multiple query parameters.
     * 
     * @param multiparam1
     * @param param123
     * @param oneMoreParam
     * @return modified string with constructor and all path query parameters
     */
    @PUT
    @Path("multiple")
    public String putMultiQueryParameter(@QueryParam("multiParam1") String multiparam1,
                                         @QueryParam("123Param") String param123,
                                         @QueryParam("1MOREParam") String oneMoreParam);

    /**
     * GET method with multiple primitive typed query parameters.
     * 
     * @param aBoolean
     * @param anInteger
     * @param aDouble
     * @param aByte
     * @param ch
     * @param aLong
     * @param aShort
     * @param aFloat
     * @return a concatenated string in method parameter order
     */
    @GET
    @Path("types/primitive")
    public String getQueryParameterPrimitiveTypes(@QueryParam("bool") Boolean aBoolean,
                                                  @QueryParam("intNumber") int anInteger,
                                                  @QueryParam("dbl") double aDouble,
                                                  @QueryParam("bite") byte aByte,
                                                  @QueryParam("ch") char ch,
                                                  @QueryParam("lng") long aLong,
                                                  @QueryParam("float") short aShort,
                                                  @QueryParam("short") float aFloat);

    /**
     * A type with a public string constructor.
     */
    public static class ParamWithStringConstructor {
        private String value = null;

        /**
         * Should not be called.
         */
        public ParamWithStringConstructor() {
            value = "noconstructor";
        }

        /**
         * Should not be called.
         * 
         * @param anInt
         */
        public ParamWithStringConstructor(Integer anInt) {
            value = "intconstructor";
        }

        /**
         * String constructor
         * 
         * @param aValue
         */
        public ParamWithStringConstructor(String aValue) {
            this.value = aValue;
        }

        /**
         * Transform the value to something else.
         * 
         * @return a transformed value
         */
        public Integer transformedValue() {
            return Integer.valueOf(value);
        }
        
        public String toString() {
        	return value;
        }
    }

    /**
     * GET method to test parameter types with a public string constructor.
     * 
     * @param param parameter which has a string constructor
     * @return a transformed value
     */
    @GET
    @Path("types/stringcstr")
    public String getQueryParameterStringConstructor(@QueryParam("paramStringConstructor") ParamWithStringConstructor param);

    /**
     * Type with a public static valueOf method to test query parameters.
     */
    public static class ParamWithValueOf {
        private String value = null;

        protected ParamWithValueOf(String aValue, int aNum) {
            value = aValue + aNum;
        }

        public ParamWithValueOf() {
        	this.value = "";
        }
        
        public ParamWithValueOf(String value) {
        	this.value = value;
        }
        
        /**
         * The transformed type value
         * 
         * @return the transformed type value
         */
        public String transformedValue() {
            return value;
        }

        /**
         * Public static valueOf method.
         * 
         * @param aValue string value to transform into type
         * @return an instance of the type
         */
        public static ParamWithValueOf valueOf(String aValue) {
            return new ParamWithValueOf(aValue, 789);
        }
        
        public String toString() {
        	return value;
        }
    }

    /**
     * GET method to test parameter with a static valueOf(String) method.
     * 
     * @param param the parameter type has a static valueOf(String) method
     * @return a transformed value
     */
    @GET
    @Path("types/valueof")
    public String getQueryParameterValueOf(@QueryParam("staticValueOf") ParamWithValueOf param);
    
}
