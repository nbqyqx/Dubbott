package com.tops001.dubbott.test.provider.service.param;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.params.CookieParamService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class CookieParamTest {

	@Autowired
	private CookieParamService cookieParamService;
	
	@Test
	public void testSwipe() {
		Response response = cookieParamService.swipe("1");
		Assert.assertEquals(200, response.getStatus());
		Assert.assertEquals("swiped:1", response.readEntity(String.class));
	}
	
}
