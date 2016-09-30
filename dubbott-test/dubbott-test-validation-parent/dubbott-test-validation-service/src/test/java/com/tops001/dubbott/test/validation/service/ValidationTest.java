package com.tops001.dubbott.test.validation.service;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.ProcessingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.validation.api.entity.MultipleEntityParamsService;
import com.tops001.dubbott.test.validation.api.formfield.FormFieldValidationService;
import com.tops001.dubbott.test.validation.api.formparam.FormParameterValidationService;
import com.tops001.dubbott.test.validation.api.formproperty.FormPropertyValidationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-validation-client.xml")
public class ValidationTest {

	@Autowired
	private MultipleEntityParamsService multipleEntityParamsService;

	@Autowired
	private FormFieldValidationService formFieldValidationService;

	@Autowired
	private FormParameterValidationService formParameterValidationService;

	@Autowired
	private FormPropertyValidationService formPropertyValidationService;

	@Test(expected = ProcessingException.class)
	public void testValidationMultipleEntities() throws Throwable {
		
		multipleEntityParamsService.getMultipleEntity("11", "22");

	}

	@Test
	public void testFormFieldNoMultivaluedMapEntityValidation() throws Exception {

		String result = formFieldValidationService.getSomething("firstkey=somevalue&someothervalue=somethingelse");
		assertEquals("null:firstkey=somevalue&someothervalue=somethingelse", result);

	}

	@Test(expected=ProcessingException.class)
	public void testFormParamNoMultivaluedMapEntityValidation() throws Exception {
		
		// cxf不支持@FormParam不能和其他参数混用，不然会抛出ProcessingException异常
		String result = formParameterValidationService.getSomething("", "somevalue");
		assertEquals("somevalue:", result);

	}

	public void testFormPropertyNoMultivaluedMapEntityValidation() throws Exception {

		// cxf框架里如果资源方法第一个参数是支持@FormParam注解的，那么将数据通过http报文体传递的时候将无法获得其他参数
		String result = formPropertyValidationService.doSomething("firstkey=somevalue&someothervalue=somethingelse", null);
		assertEquals("null:", result);

	}

}
