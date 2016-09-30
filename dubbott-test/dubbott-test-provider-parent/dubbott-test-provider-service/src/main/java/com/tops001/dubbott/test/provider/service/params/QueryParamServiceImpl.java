package com.tops001.dubbott.test.provider.service.params;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.params.QueryParamService;

/**
 * A simple resource to test <code>@QueryParam</code>.
 */
@Service
public class QueryParamServiceImpl implements QueryParamService {

    private String aQueryID = "notset";
    
    public QueryParamServiceImpl() {
    	
    }
    
    /**
     * Constructor with simple query parameter.
     * 
     * @param aQueryID a query parameter
     */
    public QueryParamServiceImpl(String aQueryID) {
        this.aQueryID = aQueryID;
    }

    @Override
	public void setAQueryId(String aQueryID) {
		this.aQueryID = aQueryID;
	}

    /**
     * GET method on root resource.
     * 
     * @return modified string with constructor query parameter
     */
    public String getConstructorQueryID() {
        return "getConstructorQueryID:" + aQueryID;
    }

    /**
     * PUT method on root resource.
     * 
     * @return modified string with constructor query parameter
     */
    public String putConstructorQueryID() {
        return "putConstructorQueryID:" + aQueryID;
    }

    /**
     * POST method on root resource.
     * 
     * @return modified string with constructor query parameter
     */
    public String postConstructorQueryID() {
        return "postConstructorQueryID:" + aQueryID;
    }

    /**
     * DELETE method on root resource.
     * 
     * @return modified string with constructor query parameter
     */
    public String deleteConstructorQueryID() {
        return "deleteConstructorQueryID:" + aQueryID;
    }

    /**
     * GET method with different path and additional query parameter.
     * 
     * @param aSimpleParameter an additional simple parameter
     * @return modified string with constructor and path query parameters
     */
    public String getSimpleQueryParameter(String aSimpleParameter) {
        return "getSimpleQueryParameter:" + aQueryID + ";" + aSimpleParameter;
    }

    /**
     * PUT method with different path and additional query parameter.
     * 
     * @param aSimpleParameter an additional simple parameter
     * @return modified string with constructor and path query parameters
     */
    public String deleteSimpleQueryParameter(String aSimpleParameter) {
        return "deleteSimpleQueryParameter:" + aQueryID + ";" + aSimpleParameter;
    }

    /**
     * POST method with different path and additional query parameter.
     * 
     * @param aSimpleParameter an additional simple parameter
     * @return modified string with constructor and path query parameters
     */
    public String putSimpleQueryParameter(String aSimpleParameter) {
        return "putSimpleQueryParameter:" + aQueryID + ";" + aSimpleParameter;
    }

    /**
     * GET method with different path and additional query parameter.
     * 
     * @param aSimpleParameter an additional simple parameter
     * @return modified string with constructor and path query parameters
     */
    public String postSimpleQueryParameter(String aSimpleParameter) {
        return "postSimpleQueryParameter:" + aQueryID + ";" + aSimpleParameter;
    }

    /**
     * GET method with multiple query parameters.
     * 
     * @param multiparam1
     * @param param123
     * @param oneMoreParam
     * @return modified string with constructor and all path query parameters
     */
    public String getMultiQueryParameter(String multiparam1,
                                         String param123,
                                         String oneMoreParam) {
        return "getMultiQueryParameter:" + aQueryID
               + ";"
               + multiparam1
               + ";"
               + param123
               + ";"
               + oneMoreParam;
    }

    /**
     * DELETE method with multiple query parameters.
     * 
     * @param multiparam1
     * @param param123
     * @param oneMoreParam
     * @return modified string with constructor and all path query parameters
     */
    public String deleteMultiQueryParameter(String multiparam1,
                                            String param123,
                                            String oneMoreParam) {
        return "deleteMultiQueryParameter:" + aQueryID
               + ";"
               + multiparam1
               + ";"
               + param123
               + ";"
               + oneMoreParam;
    }

    /**
     * POST method with multiple query parameters.
     * 
     * @param multiparam1
     * @param param123
     * @param oneMoreParam
     * @return modified string with constructor and all path query parameters
     */
    public String postMultiQueryParameter(String multiparam1,
                                          String param123,
                                          String oneMoreParam) {
        return "postMultiQueryParameter:" + aQueryID
               + ";"
               + multiparam1
               + ";"
               + param123
               + ";"
               + oneMoreParam;
    }

    /**
     * PUT method with multiple query parameters.
     * 
     * @param multiparam1
     * @param param123
     * @param oneMoreParam
     * @return modified string with constructor and all path query parameters
     */
    public String putMultiQueryParameter(String multiparam1,
                                         String param123,
                                         String oneMoreParam) {
        return "putMultiQueryParameter:" + aQueryID
               + ";"
               + multiparam1
               + ";"
               + param123
               + ";"
               + oneMoreParam;
    }

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
    public String getQueryParameterPrimitiveTypes(Boolean aBoolean,
                                                  int anInteger,
                                                  double aDouble,
                                                  byte aByte,
                                                  char ch,
                                                  long aLong,
                                                  short aShort,
                                                  float aFloat) {
        return "getQueryParameterPrimitiveTypes:" + aBoolean
               + ";"
               + anInteger
               + ";"
               + aDouble
               + ";"
               + aByte
               + ";"
               + ch
               + ";"
               + aLong
               + ";"
               + aShort
               + ";"
               + aFloat;
    }


    /**
     * GET method to test parameter types with a public string constructor.
     * 
     * @param param parameter which has a string constructor
     * @return a transformed value
     */
    public String getQueryParameterStringConstructor(ParamWithStringConstructor param) {
        return "getQueryParameterStringConstructor:" + param.transformedValue();
    }

    /**
     * GET method to test parameter with a static valueOf(String) method.
     * 
     * @param param the parameter type has a static valueOf(String) method
     * @return a transformed value
     */
    public String getQueryParameterValueOf(ParamWithValueOf param) {
        return "getQueryParameterValueOf:" + param.transformedValue();
    }

}
