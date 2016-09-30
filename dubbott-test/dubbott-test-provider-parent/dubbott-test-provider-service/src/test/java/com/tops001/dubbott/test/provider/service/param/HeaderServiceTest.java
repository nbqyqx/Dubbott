package com.tops001.dubbott.test.provider.service.param;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.params.HeaderParamService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class HeaderServiceTest {
	
	@Autowired
	private HeaderParamService headerParamService;
	
	/**
	 * 无法采用dubbott的客户端进行测试，因为dubbott客户端在这种情况下无法传递默认的@MatrixParam参数。<br/>
	 * 如果是get请求，那么可以使用http客户端进行参数传递。<br/>
	 * @throws Exception
	 */
    @Test
    @Ignore
    public void testCustomHeaderParam() throws Exception {
        headerParamService.setCstrHeaderParam("somevalue");
        headerParamService.setUserAgent("httpclient");
        Response response = headerParamService.getHeaderParam("en");
        String result = response.readEntity(String.class);
        assertEquals("secret", (String)response.getHeaders().getFirst("custResponseHeader"));
        assertEquals("getHeaderParam:somevalue;User-Agent:httpclient;Accept-Language:en;language-method:en",
        		result);
    }
	
}
