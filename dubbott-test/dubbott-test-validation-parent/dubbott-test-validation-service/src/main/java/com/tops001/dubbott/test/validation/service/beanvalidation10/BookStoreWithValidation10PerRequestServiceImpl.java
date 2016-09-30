package com.tops001.dubbott.test.validation.service.beanvalidation10;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.validation.api.beanvalidation10.BookStoreWithValidation10PerRequestService;
import com.tops001.dubbott.test.validation.model.beanvalidation10.BookWithValidation;

@Service
public class BookStoreWithValidation10PerRequestServiceImpl implements BookStoreWithValidation10PerRequestService {
    private final Map<String, BookWithValidation> books = new HashMap<String, BookWithValidation>();
    private final Map<String, BookWithValidation> bookResponse = new HashMap<String, BookWithValidation>();

    private final BookWithValidation bookNeal = new BookWithValidation("Neal", "007");
    private final BookWithValidation bookEmptyName = new BookWithValidation("124");

    public BookStoreWithValidation10PerRequestServiceImpl() {
        books.put("007", bookNeal);
        books.put("124", bookEmptyName);
        bookResponse.put("007", bookNeal);
        bookResponse.put("124", bookEmptyName);
    }

    public Response getBookResponse(String id) {
        return Response.ok(bookResponse.get(id)).build();
    }
    
}