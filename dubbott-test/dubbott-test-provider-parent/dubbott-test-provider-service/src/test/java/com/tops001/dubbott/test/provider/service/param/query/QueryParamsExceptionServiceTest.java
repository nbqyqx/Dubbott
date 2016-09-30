package com.tops001.dubbott.test.provider.service.param.query;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.params.query.QueryParamsExceptionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class QueryParamsExceptionServiceTest {

	@SuppressWarnings("unused")
	@Autowired
	private QueryParamsExceptionService queryParamsExceptionService;
	
	// This test can't use sendGoodRequestAndGetResponse() because that method
    // expects a 200; these requests are different and are meant to result
    // in exceptions thrown
    @Test
    @Ignore("dubbott客户端不支持传递非当前资源方法的参数，如：CustomStringConstructorFieldQuery")
    public void testQueryParamException() throws Exception {
    	
    }
	
}
