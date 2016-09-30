package com.tops001.dubbott.test.provider.api.params;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 * A simple resource to test <code>@MatrixParam</code>.
 */
@Path("/matrix")
public interface MatrixParamService {

    /**
     * setAConstructorParam.
     * 
     * @param cstrparam a simple <code>@MatrixParam</code> method
     *            parameter
     */
    public void setAConstructorParam(@MatrixParam("cstrparam") String cstrparam);

    /**
     * GET method for constructor matrix parameter.
     * 
     * @return transformed string
     */
    @GET
    public String getConstructorMatrixParam();

    /**
     * POST method for constructor matrix parameter.
     * 
     * @return transformed string
     */
    @POST
    public String postConstructorMatrixParam();

    /**
     * PUT method for constructor matrix parameter.
     * 
     * @return transformed string
     */
    @PUT
    public String putConstructorMatrixParam();

    /**
     * DELETE method for constructor matrix parameter.
     * 
     * @return transformed string
     */
    @DELETE
    public String deleteConstructorMatrixParam();

    /**
     * GET method for simple matrix parameter.
     * 
     * @param life simple parameter
     * @return transformed string
     */
    @GET
    @Path("simple")
    public String getSimpleMatrixParam(@MatrixParam("life") String life);

    /**
     * POST method for simple matrix parameter.
     * 
     * @param life simple parameter
     * @return transformed string
     */
    @POST
    @Path("simple")
    public String postSimpleMatrixParam(@MatrixParam("life") String life);

    /**
     * PUT method for simple matrix parameter.
     * 
     * @param life simple parameter
     * @return transformed string
     */
    @PUT
    @Path("simple")
    public String putSimpleMatrixParam(@MatrixParam("life") String life);

    /**
     * DELETE method for simple matrix parameter.
     * 
     * @param life simple parameter
     * @return transformed string
     */
    @DELETE
    @Path("simple")
    public String deleteSimpleMatrixParam(@MatrixParam("life") String life);

    /**
     * GET method for multiple matrix parameters.
     * 
     * @param first
     * @param uppercaseOneMoreParam
     * @param lowercaseOneMoreParam
     * @return transformed string
     */
    @GET
    @Path("multiple")
    public String getMultipleMatrixParam(@MatrixParam("1st") String first,
                                         @MatrixParam("ONEMOREPARAM") String uppercaseOneMoreParam,
                                         @MatrixParam("onemoreparam") String lowercaseOneMoreParam);

    /**
     * POST method for multiple matrix parameters.
     * 
     * @param first
     * @param uppercaseOneMoreParam
     * @param lowercaseOneMoreParam
     * @return transformed string
     */
    @POST
    @Path("multiple")
    public String postMultipleMatrixParam(@MatrixParam("1st") String first,
                                          @MatrixParam("ONEMOREPARAM") String uppercaseOneMoreParam,
                                          @MatrixParam("onemoreparam") String lowercaseOneMoreParam);

    /**
     * PUT method for multiple matrix parameters.
     * 
     * @param first
     * @param uppercaseOneMoreParam
     * @param lowercaseOneMoreParam
     * @return transformed string
     */
    @PUT
    @Path("multiple")
    public String putMultipleMatrixParam(@MatrixParam("1st") String first,
                                         @MatrixParam("ONEMOREPARAM") String uppercaseOneMoreParam,
                                         @MatrixParam("onemoreparam") String lowercaseOneMoreParam);

    /**
     * DELETE method for multiple matrix parameters.
     * 
     * @param first
     * @param uppercaseOneMoreParam
     * @param lowercaseOneMoreParam
     * @return transformed string
     */
    @DELETE
    @Path("multiple")
    public String deleteMultipleMatrixParam(@MatrixParam("1st") String first,
                                            @MatrixParam("ONEMOREPARAM") String uppercaseOneMoreParam,
                                            @MatrixParam("onemoreparam") String lowercaseOneMoreParam);

    /**
     * GET method to test primitive matrix typed parameters
     * 
     * @param aBoolean
     * @param anInteger
     * @param aDouble
     * @param aByte
     * @param ch
     * @param aLong
     * @param aShort
     * @param aFloat
     * @return a transformed string
     */
    @GET
    @Path("types/primitive")
    public String getMatrixPrimitiveTypes(@MatrixParam("bool") Boolean aBoolean,
                                          @MatrixParam("intNumber") int anInteger,
                                          @MatrixParam("dbl") double aDouble,
                                          @MatrixParam("bite") byte aByte,
                                          @MatrixParam("ch") char ch,
                                          @MatrixParam("lng") long aLong,
                                          @MatrixParam("float") short aShort,
                                          @MatrixParam("short") float aFloat);

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
     * GET method to test matrix parameter types with a public string
     * constructor.
     * 
     * @param param parameter which has a string constructor
     * @return a transformed value
     */
    @GET
    @Path("types/stringcstr")
    public String getQueryParameterStringConstructor(@MatrixParam("paramStringConstructor") ParamWithStringConstructor param);

    /**
     * Type with a public static valueOf method to test query parameters.
     */
    public static class ParamWithValueOf {
        private String value = null;

        protected ParamWithValueOf(String aValue, int aNum) {
            value = aValue + aNum;
        }
        
        protected ParamWithValueOf(String aValue) {
            value = aValue;
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
        
        /**
         * Public static valueOf method.
         * 
         * @param aValue string value to transform into type
         * @return an instance of the type
         */
        public static ParamWithValueOf valueOfWithoutHandle(String aValue) {
            return new ParamWithValueOf(aValue);
        }
        
        public String toString() {
        	return value;
        }
    }

    /**
     * GET method to test matrix parameter with a static valueOf(String) method.
     * 
     * @param param the parameter type has a static valueOf(String) method
     * @return a transformed value
     */
    @GET
    @Path("types/valueof")
    public String getQueryParameterValueOf(@MatrixParam("staticValueOf") ParamWithValueOf param);
    
}
