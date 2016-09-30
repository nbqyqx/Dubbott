package com.tops001.dubbott.test.validation.api.beanvalidation;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.tops001.dubbott.test.validation.model.beanvalidation.BookWithValidation;

@Path("/bookstore/")
public interface BookStoreWithValidationService extends BookStoreValidatable {

    @GET
    @Path("/books/{bookId}")
    @Override
    @NotNull
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public BookWithValidation getBook(@PathParam("bookId") String id);

    @GET
    @Path("/booksResponse/{bookId}")
    @Valid
    @NotNull
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response getBookResponse(@PathParam("bookId") String id);

    @GET
    @Path("/booksResponseNoValidation/{bookId}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response getBookResponseNoValidation(@PathParam("bookId") String id);

    @POST
    @Path("/books")
    public Response addBook(@Context final UriInfo uriInfo,
                            @NotNull @Size(min = 1, max = 50) @FormParam("id") String id,
                            @FormParam("name") String name);

    @POST
    @Path("/books/direct")
    @Consumes("text/xml")
    public Response addBookDirect(@Valid BookWithValidation book, @Context final UriInfo uriInfo);

    @POST
    @Path("/books/directmany")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response addBooksDirect(@Valid List<BookWithValidation> list, @Context final UriInfo uriInfo);

    @Override
    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Collection<BookWithValidation> list(@DefaultValue("1") @QueryParam("page") int page);

    @DELETE
    @Path("/books")
    public Response clear();
}
