package com.tops001.dubbott.test.provider.service.params;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.params.MatrixParamService;

/**
 * A simple resource to test <code>@MatrixParam</code>.
 */
@Service
public class MatrixParamServiceImpl implements MatrixParamService {

    private String param;
    
    @Override
	public void setAConstructorParam(String cstrparam) {
    	this.param = cstrparam;
	}

    /**
     * GET method for constructor matrix parameter.
     * 
     * @return transformed string
     */
    public String getConstructorMatrixParam() {
        return "getConstructorMatrixParam:" + param;
    }

    /**
     * POST method for constructor matrix parameter.
     * 
     * @return transformed string
     */
    public String postConstructorMatrixParam() {
        return "postConstructorMatrixParam:" + param;
    }

    /**
     * PUT method for constructor matrix parameter.
     * 
     * @return transformed string
     */
    public String putConstructorMatrixParam() {
        return "putConstructorMatrixParam:" + param;
    }

    /**
     * DELETE method for constructor matrix parameter.
     * 
     * @return transformed string
     */
    public String deleteConstructorMatrixParam() {
        return "deleteConstructorMatrixParam:" + param;
    }

    /**
     * GET method for simple matrix parameter.
     * 
     * @param life simple parameter
     * @return transformed string
     */
    public String getSimpleMatrixParam(String life) {
        return "getSimpleMatrixParam:" + param + ";" + life;
    }

    /**
     * POST method for simple matrix parameter.
     * 
     * @param life simple parameter
     * @return transformed string
     */
    public String postSimpleMatrixParam(String life) {
        return "postSimpleMatrixParam:" + param + ";" + life;
    }

    /**
     * PUT method for simple matrix parameter.
     * 
     * @param life simple parameter
     * @return transformed string
     */
    public String putSimpleMatrixParam(String life) {
        return "putSimpleMatrixParam:" + param + ";" + life;
    }

    /**
     * DELETE method for simple matrix parameter.
     * 
     * @param life simple parameter
     * @return transformed string
     */
    public String deleteSimpleMatrixParam(String life) {
        return "deleteSimpleMatrixParam:" + param + ";" + life;
    }

    /**
     * GET method for multiple matrix parameters.
     * 
     * @param first
     * @param uppercaseOneMoreParam
     * @param lowercaseOneMoreParam
     * @return transformed string
     */
    public String getMultipleMatrixParam(String first, String uppercaseOneMoreParam, 
    		String lowercaseOneMoreParam) {
        return "getMultipleMatrixParam:" + first
               + ";"
               + uppercaseOneMoreParam
               + ";"
               + lowercaseOneMoreParam;
    }

    /**
     * POST method for multiple matrix parameters.
     * 
     * @param first
     * @param uppercaseOneMoreParam
     * @param lowercaseOneMoreParam
     * @return transformed string
     */
    public String postMultipleMatrixParam(String first, String uppercaseOneMoreParam, 
    		String lowercaseOneMoreParam) {
        return "postMultipleMatrixParam:" + first
               + ";"
               + uppercaseOneMoreParam
               + ";"
               + lowercaseOneMoreParam;
    }

    /**
     * PUT method for multiple matrix parameters.
     * 
     * @param first
     * @param uppercaseOneMoreParam
     * @param lowercaseOneMoreParam
     * @return transformed string
     */
    public String putMultipleMatrixParam(String first, String uppercaseOneMoreParam, 
    		String lowercaseOneMoreParam) {
        return "putMultipleMatrixParam:" + first
               + ";"
               + uppercaseOneMoreParam
               + ";"
               + lowercaseOneMoreParam;
    }

    /**
     * DELETE method for multiple matrix parameters.
     * 
     * @param first
     * @param uppercaseOneMoreParam
     * @param lowercaseOneMoreParam
     * @return transformed string
     */
    public String deleteMultipleMatrixParam(String first, String uppercaseOneMoreParam, 
    		String lowercaseOneMoreParam) {
        return "deleteMultipleMatrixParam:" + first
               + ";"
               + uppercaseOneMoreParam
               + ";"
               + lowercaseOneMoreParam;
    }

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
    public String getMatrixPrimitiveTypes(Boolean aBoolean, int anInteger, double aDouble,
    		byte aByte, char ch, long aLong, short aShort, float aFloat) {
        return "getMatrixParameterPrimitiveTypes:" + aBoolean
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
     * GET method to test matrix parameter types with a public string
     * constructor.
     * 
     * @param param parameter which has a string constructor
     * @return a transformed value
     */
    public String getQueryParameterStringConstructor(ParamWithStringConstructor param) {
        return "getMatrixParameterStringConstructor:" + param.transformedValue();
    }

    /**
     * GET method to test matrix parameter with a static valueOf(String) method.
     * 
     * @param param the parameter type has a static valueOf(String) method
     * @return a transformed value
     */
    public String getQueryParameterValueOf(ParamWithValueOf param) {
        return "getMatrixParameterValueOf:" + param.transformedValue();
    }

}
