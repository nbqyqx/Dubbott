package com.tops001.dubbott.test.provider.service.param;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.params.DiffCaptureVariablesParamsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class DiffCaptureVariablesParamsServiceTest {

	@Autowired
	private DiffCaptureVariablesParamsService diffCaptureVariablesParamsService;
	
	/**
     * Resource methods echo the parameters given to it, specified in path
     * Each method should send back a non-null value.
     */
    @Test
    public void testDiffCaptureVariableNames() throws Exception {

        String result = diffCaptureVariablesParamsService.getMethod("1234");
        assertEquals("id1_1234", result);

        result = diffCaptureVariablesParamsService.doSomething("5678");
        assertEquals("id2_5678", result);

        result = diffCaptureVariablesParamsService.deleteMethod("0001");
        assertEquals("id3_0001", result);
    }
	
}
