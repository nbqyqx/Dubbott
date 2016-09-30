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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.tops001.dubbott.demo.beanvalidation.BookStoreWithValidation;
import com.tops001.dubbott.demo.beanvalidation.BookWithValidation;

public class BookStoreWithValidationImpl implements BookStoreWithValidation {
    private final Map<String, BookWithValidation> books = new HashMap<String, BookWithValidation>();

    public BookWithValidation getBook(@PathParam("bookId") String id) {
        return books.get(id);
    }

    public Response getBookResponse(@PathParam("bookId") String id) {
        return Response.ok(books.get(id)).build();
    }

    public Response getBookResponseNoValidation(@PathParam("bookId") String id) {
        return Response.ok(books.get(id)).build();
    }

    public Response addBook(@Context final UriInfo uriInfo,
                            @NotNull @Size(min = 1, max = 50) @FormParam("id") String id,
                            @FormParam("name") String name) {
        books.put(id, new BookWithValidation(name, id));
        return Response.created(uriInfo.getRequestUriBuilder().path(id).build()).build();
    }

    public Response addBookDirect(@Valid BookWithValidation book, @Context final UriInfo uriInfo) {
        books.put(book.getId(), book);
        return Response.created(uriInfo.getRequestUriBuilder().path(book.getId()).build()).build();
    }

    public Response addBooksDirect(@Valid List<BookWithValidation> list, @Context final UriInfo uriInfo) {
        books.put(list.get(0).getId(), list.get(0));
        return Response.created(uriInfo.getRequestUriBuilder().path(list.get(0).getId()).build()).build();
    }

    public Collection<BookWithValidation> list(@DefaultValue("1") @QueryParam("page") int page) {
        return books.values();
    }

    public Response clear() {
        books.clear();
        return Response.ok().build();
    }
}
