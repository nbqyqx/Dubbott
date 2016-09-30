package com.tops001.dubbott.test.validation.service.beanvalidation;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.rpc.RpcException;
import com.tops001.dubbott.test.validation.api.beanvalidation.BookStoreWithValidationService;
import com.tops001.dubbott.test.validation.model.beanvalidation.BookWithValidation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-validation-client.xml")
public class JAXRSClientServerValidationTest {

	@Autowired
	private BookStoreWithValidationService bookStoreWithValidationService;

	@Test(expected = InternalServerErrorException.class)
	public void testThatPatternValidationFails() throws Exception {
		BookWithValidation bookWithValidation = bookStoreWithValidationService.getBook("blabla");
		System.out.println(bookWithValidation);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatNotNullValidationFails() throws Exception {
		BookWithValidation bookWithValidation = bookStoreWithValidationService.getBook(null);
		System.out.println(bookWithValidation);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testThatSizeValidationFails() throws Throwable {
		
		try {
			bookStoreWithValidationService.addBook(null, "", "");
		} catch(Exception e) {
			throw e.getCause();
		}
		
	}

	@Test(expected = InternalServerErrorException.class)
	public void testThatMinValidationFails() throws Exception {
		Collection<BookWithValidation> bookWithValidations = bookStoreWithValidationService.list(0);
		System.out.println(bookWithValidations);
	}

	@Test
	public void testThatNoValidationConstraintsAreViolatedWithBook() throws Exception {
		BookWithValidation book = new BookWithValidation("BeanVal", "1");
		Response response = bookStoreWithValidationService.addBookDirect(book, null);
		assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
	}

	@Test
	@Ignore("客户端无法构造UriInfo实例进行传递")
	public void testThatValidationConstraintsAreViolatedWithBook() throws Exception {

		BookWithValidation book = new BookWithValidation("BeanVal");
		Response response = bookStoreWithValidationService.addBookDirect(book, null);
		System.out.println(response.getStatus());
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());

	}

	@Test(expected = ConstraintViolationException.class)
	public void testThatValidationConstraintsAreViolatedWithBooks() throws Throwable {

		BookWithValidation book = new BookWithValidation("BeanVal");
		try {
			bookStoreWithValidationService.addBooksDirect(Collections.singletonList(book), null);
		} catch (RpcException e) {
			throw e.getCause();
		}

	}

	@Test
	public void testThatResponseValidationForOneBookFails() throws Exception {

		Response response = bookStoreWithValidationService.addBook(null, "1234", null);
		assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
		response = bookStoreWithValidationService.getBookResponse("1234");
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
	}

	@Test
	public void testThatResponseValidationForOneBookNotFails() throws Exception {

		Response response = bookStoreWithValidationService.addBook(null, "1235", "cxf");
		assertEquals(Status.CREATED.getStatusCode(), response.getStatus());

		response = bookStoreWithValidationService.getBookResponse("1235");
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	@Test
	public void testThatResponseValidationForNullBookFails() throws Exception {

		Response response = bookStoreWithValidationService.addBook(null, "1236", "cxf");
		assertEquals(Status.CREATED.getStatusCode(), response.getStatus());

		response = bookStoreWithValidationService.getBookResponse("1237");
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());

	}

	@Test
	public void testThatResponseValidationForOneResponseBookFails() throws Exception {

		// Will double check why 129 is not correct for this case later
		Response response = bookStoreWithValidationService.getBookResponse("129");
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());

		response = bookStoreWithValidationService.addBook(null, "129", null);
		assertEquals(Status.CREATED.getStatusCode(), response.getStatus());

		response = bookStoreWithValidationService.getBookResponse("129");
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());

	}

	@Test
	public void testThatResponseValidationForBookPassesWhenNoConstraintsAreDefined() throws Exception {

		String bookId = UUID.randomUUID().toString();
		Response response = bookStoreWithValidationService.getBookResponse(bookId);
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());

		response = bookStoreWithValidationService.addBook(null, bookId, "apache");
		assertEquals(Status.CREATED.getStatusCode(), response.getStatus());

		response = bookStoreWithValidationService.getBookResponse(bookId);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

}