package com.tops001.dubbott.test.provider.service.webcontainer;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.webcontainer.WebContainerContextInjectionService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class WebContainerContextInjectionServiceTest {
	
	@Autowired
	private WebContainerContextInjectionService webContainerContextInjectionService;
	
	@Test
	public void testFetchHTTPRequestPathInfo () {
		String pathInfo = webContainerContextInjectionService.fetchHTTPRequestPathInfo(null);
		Assert.assertEquals("/environment/webcontainer/context/", pathInfo);
	}
	
	@Test
	public void testFetchHTTPResponse () {
		String result = webContainerContextInjectionService.fetchHTTPResponse(null);
		Assert.assertEquals("Hello World -- I was committted", result);
	}
	
	@Test
	@Ignore("忽略携带jsp的测试")
	public void testFetchServletContext () throws IOException, ServletException {
		// ignore request attribute test
		webContainerContextInjectionService.fetchServletContext(null, null, null);
	}
	
	@Test
	public void testFetchServletConfig () {
		String servletConfig = webContainerContextInjectionService.fetchServletConfig(null);
		Assert.assertEquals("DispatcherServlet", servletConfig);
	}

}