/*******************************************************************************
 * Copyright (c) 2016 Tops Tech Corp.
 *
 *
 * This software is the confidential and proprietary information of
 * Tops Tech Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with tops001.com. 
 * *******************************************************************************/

package com.tops001.dubbott.demo.beanvalidation;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.tops001.dubbott.demo.beanvalidation.BookStoreWithValidationPerRequest;
import com.tops001.dubbott.demo.beanvalidation.BookWithValidation;


public class BookStoreWithValidationPerRequestImpl implements BookStoreWithValidationPerRequest{
    private final Map<String, BookWithValidation> books = new HashMap<String, BookWithValidation>();
    @NotNull
    private String id;

    public BookStoreWithValidationPerRequestImpl() {
        books.put("123", new BookWithValidation("CXF", "123"));
        books.put("124", new BookWithValidation("124"));
    }

    @QueryParam("id")
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public BookWithValidation book() {
        return books.get(id);
    }

    public Response bookResponse() {
        return Response.ok(books.get(id)).build();
    }
}