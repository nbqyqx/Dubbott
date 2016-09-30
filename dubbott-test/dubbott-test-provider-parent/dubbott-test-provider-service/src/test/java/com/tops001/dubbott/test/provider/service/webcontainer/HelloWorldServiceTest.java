package com.tops001.dubbott.test.provider.service.webcontainer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.webcontainer.HelloWorldService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class HelloWorldServiceTest {
	
	@Autowired
	private HelloWorldService helloWorldService;
	
	/**
	 * 该方法会在所有测试方法前调用，目的是为了保证testGetMessage方法正确，原因是
	 * 我们不确定@Test方法的执行顺序。
	 */
	@Before
	public void before() {
		helloWorldService.postMessage("Hello World!");
	}
	
	@Test
	public void testGetMessage() {
		Assert.assertEquals("Hello World!", helloWorldService.getMessage());
	}
	
	@Test
	public void testPostMessage() {
		Assert.assertEquals("hello world again!", helloWorldService.postMessage("hello world again!"));
	}
	
	@Test
	public void testPutMessage() {
		Assert.assertEquals("put test", helloWorldService.putMessage("put test".getBytes()).readEntity(String.class));
	}
	
	@Test
	public void testDeleteMessage() {
		Assert.assertEquals("", helloWorldService.deleteMessage().readEntity(String.class));
	}

}