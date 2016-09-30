package com.tops001.dubbott.test.validation.service.beanvalidtion;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.validation.api.beanvalidation.BookStoreWithValidationService;
import com.tops001.dubbott.test.validation.model.beanvalidation.BookWithValidation;

@Service
public class BookStoreWithValidationServiceImpl implements BookStoreWithValidationService {

	private final Map<String, BookWithValidation> books = new HashMap<String, BookWithValidation>();

	public BookStoreWithValidationServiceImpl() {
		books.put("123", new BookWithValidation("CXF", "123"));
        books.put("124", new BookWithValidation("124"));
	}

	public BookWithValidation getBook(String id) {
		return books.get(id);
	}

	public Response getBookResponse(String id) {
		return Response.ok(books.get(id)).build();
	}

	public Response getBookResponseNoValidation(String id) {
		return Response.ok(books.get(id)).build();
	}

	public Response addBook(final UriInfo uriInfo, String id, String name) {
		books.put(id, new BookWithValidation(name, id));
		return Response.created(uriInfo.getRequestUriBuilder().path(id).build()).build();
	}

	public Response addBookDirect(BookWithValidation book, final UriInfo uriInfo) {
		books.put(book.getId(), book);
		return Response.created(uriInfo.getRequestUriBuilder().path(book.getId()).build()).build();
	}

	public Response addBooksDirect(List<BookWithValidation> list, final UriInfo uriInfo) {
		books.put(list.get(0).getId(), list.get(0));
		return Response.created(uriInfo.getRequestUriBuilder().path(list.get(0).getId()).build()).build();
	}

	public Collection<BookWithValidation> list(int page) {
		return books.values();
	}

	public Response clear() {
		books.clear();
		return Response.ok().build();
	}
	
}
