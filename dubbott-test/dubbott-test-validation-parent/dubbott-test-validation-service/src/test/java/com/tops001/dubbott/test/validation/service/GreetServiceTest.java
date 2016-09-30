package com.tops001.dubbott.test.validation.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.validation.api.GreetService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-validation-client.xml")
public class GreetServiceTest {
	
	@Autowired
	private GreetService greetService;
	
	@Test
	public void testWelcome() {
		Assert.assertEquals("Hello linsen!", greetService.welcome("linsen"));
	}

}