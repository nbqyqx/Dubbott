package com.tops001.dubbott.test.validation.service.beanvalidation;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tops001.dubbott.test.validation.api.beanvalidation10.BookStoreWithValidation10PerRequestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-validation-client.xml")
public class JAXRSValidationDisabledTest {
	
	@Autowired
	private BookStoreWithValidation10PerRequestService bookStoreWithValidation10PerRequestService;

    @Test
    public void testThatValidationConstraintsAreViolatedWhenBookDoesNotExistResponse_Disabled() throws Exception {
    	Response response = bookStoreWithValidation10PerRequestService.getBookResponse("007");
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testThatValidationConstraintsAreViolatedWhenBookNameIsNotSet_Disabled() throws Exception {
    	Response response = bookStoreWithValidation10PerRequestService.getBookResponse("124");
        assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }

}