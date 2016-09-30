package com.tops001.dubbott.test.provider.service.param;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.provider.api.params.form.FormParamService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class FormParamServiceTest {
	
	@Autowired
	private FormParamService formParamService;
	
	@Test
	public void testGetRes1 () {
		MultivaluedMap<String, String> entity = new MultivaluedHashMap<>();
		List<String> list = new ArrayList<>();
		list.add("111");
		list.add("2222");
		entity.put("abc", list);
		String result = formParamService.getRes(entity);
		Assert.assertEquals("{abc=[111, 2222]}", result);
	}
	
	/**
	 * dubbott客户端对同时使用@FormParam和MultivaluedMap类型的服务支持有问题
	 */
	@Test
	@Ignore
	public void testGetRes2 () {
		MultivaluedMap<String, String> entity = new MultivaluedHashMap<>();
		List<String> list = new ArrayList<>();
		list.add("111");
		list.add("2222");
		entity.put("abc", list);
		String result = formParamService.getRes("firstKey01", entity);
		System.out.println(result);
	}
	
	@Test()
	public void testGetStrEntity () {
		String result = formParamService.getStrEntity("test001");
		Assert.assertEquals("str:test001", result);
	}
	
}