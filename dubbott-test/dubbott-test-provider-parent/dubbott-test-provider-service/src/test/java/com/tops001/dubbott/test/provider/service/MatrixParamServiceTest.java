package com.tops001.dubbott.test.provider.service;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.params.MatrixParamService;
import com.tops001.dubbott.test.provider.api.params.MatrixParamService.ParamWithStringConstructor;
import com.tops001.dubbott.test.provider.api.params.MatrixParamService.ParamWithValueOf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class MatrixParamServiceTest {
	
	@Autowired
	private MatrixParamService matrixParamService;
	
	/**
     * Tests that no matrix parameters sent still calls proper resource.
     */
	@Test
	public void testNoParam(){
		assertEquals("deleteConstructorMatrixParam:null", matrixParamService.deleteConstructorMatrixParam());
		assertEquals("getConstructorMatrixParam:null", matrixParamService.getConstructorMatrixParam());
		assertEquals("putConstructorMatrixParam:null", matrixParamService.putConstructorMatrixParam());
		assertEquals("postConstructorMatrixParam:null", matrixParamService.postConstructorMatrixParam());
	}
	
	/**
     * Tests the constructor matrix parameter is processed.
     */
    @Test
    @Ignore("dubbott客户端无法注入非资源方法的信息, cstrparam=HelloWorld")
    public void testConstructorParam() throws Exception {
        assertEquals("getConstructorMatrixParam:HelloWorld", matrixParamService.getConstructorMatrixParam());
        assertEquals("deleteConstructorMatrixParam:HelloWorld", matrixParamService.deleteConstructorMatrixParam());
        assertEquals("putConstructorMatrixParam:HelloWorld", matrixParamService.putConstructorMatrixParam());
        assertEquals("postConstructorMatrixParam:HelloWorld", matrixParamService.postConstructorMatrixParam());
    }
    
    /**
     * Tests both the simple constructor and method matrix parameter are
     * processed.
     */
    @Test
    @Ignore("dubbott客户端无法注入非资源方法的信息, cstrparam=Hello")
    public void testSimpleMatrixParam() throws Exception {
        assertEquals("getSimpleMatrixParam:Hello;good", matrixParamService.getSimpleMatrixParam("good"));
        assertEquals("putSimpleMatrixParam:Hello;good", matrixParamService.putSimpleMatrixParam("good"));
        assertEquals("postSimpleMatrixParam:Hello;good", matrixParamService.postSimpleMatrixParam("good"));
        assertEquals("deleteSimpleMatrixParam:Hello;good", matrixParamService.deleteSimpleMatrixParam("good"));
    }
    
    /**
     * Tests that a no constructor matrix parameter is set.
     */
    @Test
    public void testNoConstructorMatrixParamAndSimpleMatrixParam() throws Exception {
        assertEquals("deleteSimpleMatrixParam:null;erase", matrixParamService.deleteSimpleMatrixParam("erase"));
        assertEquals("getSimpleMatrixParam:null;good", matrixParamService.getSimpleMatrixParam("good"));
        assertEquals("postSimpleMatrixParam:null;new", matrixParamService.postSimpleMatrixParam("new"));
        assertEquals("putSimpleMatrixParam:null;progress", matrixParamService.putSimpleMatrixParam("progress"));
    }
    
    /**
     * Tests the constructor and simple matrix parameter can be out of order.
     */
    @Test
    @Ignore("dubbott客户端无法注入非资源方法的信息, cstrparam=Hello")
    public void testOutOfOrderMatrixParam() throws Exception {
        assertEquals("getSimpleMatrixParam:Hello;good", matrixParamService.getSimpleMatrixParam("good"));
        assertEquals("putSimpleMatrixParam:Hello;good", matrixParamService.putSimpleMatrixParam("good"));
        assertEquals("postSimpleMatrixParam:Hello;good", matrixParamService.postSimpleMatrixParam("good"));
        assertEquals("deleteSimpleMatrixParam:Hello;good", matrixParamService.deleteSimpleMatrixParam("good"));
    }
    
    /**
     * Tests multiple matrix parameters sent to same resource.
     */
    @Test
    public void testMultipleMatrixParam() throws Exception {
        assertEquals("getMultipleMatrixParam:first;capital;done", matrixParamService.getMultipleMatrixParam("first", "capital", "done"));
        assertEquals("deleteMultipleMatrixParam:first;capital;done", matrixParamService.deleteMultipleMatrixParam("first", "capital", "done"));
        assertEquals("postMultipleMatrixParam:first;capital;done", matrixParamService.postMultipleMatrixParam("first", "capital", "done"));
        assertEquals("putMultipleMatrixParam:first;capital;done", matrixParamService.putMultipleMatrixParam("first", "capital", "done"));
    }
    
    /**
     * Tests that primitive types are accepted in matrix parameters.
     */
    @Test
    public void testPrimitiveTypedMatrixParameter() throws Exception {
        assertEquals("getMatrixParameterPrimitiveTypes:false;12;3.14;3;b;1234567890;32456;123.0", 
        		matrixParamService.getMatrixPrimitiveTypes(false, 12, 3.14, (byte)3, 'b', 1234567890, (short)32456, (float)123.0));
                //"matrix/types/primitive;bool=false;intNumber=12;dbl=3.14;bite=3;ch=b;lng=1234567890;float=32456;short=123",
    }

    /**
     * Tests that primitive types are accepted in parameters.
     */
    @Test
    public void testParameterTypeWithStringConstructor() throws Exception {
        assertEquals("getMatrixParameterStringConstructor:1234", matrixParamService.getQueryParameterStringConstructor(new ParamWithStringConstructor("1234")));
    }

    /**
     * Tests that primitive types are accepted in parameters.
     */
    @Test
    public void testParameterTypeWithValueOfMethod() throws Exception {
        assertEquals("getMatrixParameterValueOf:456789", matrixParamService.getQueryParameterValueOf(ParamWithValueOf.valueOfWithoutHandle("456")));
    }

}
