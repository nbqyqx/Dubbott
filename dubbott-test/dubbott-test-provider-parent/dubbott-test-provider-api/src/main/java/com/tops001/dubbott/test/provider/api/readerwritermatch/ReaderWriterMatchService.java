package com.tops001.dubbott.test.provider.api.readerwritermatch;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.transform.Source;

@Path("resource")
public interface ReaderWriterMatchService {

    public static final String HEADERNAME = "FILTER_HEADER";
    public static final String DIRECTION = "FROM_RESOURCE";

    @Path("source")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Source source(Source source);

    @Path("jaxb")
    @POST
    public JAXBElement<String> jaxb(JAXBElement<String> jaxb);

    @Path("map")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public MultivaluedMap<String, String> map(MultivaluedMap<String, String> map);

    @Path("character")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Character character(Character character);

    @GET
    @Path("text")
    @Produces(value = "text/*")
    public String geText();

    @Path("boolean")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Boolean bool(Boolean bool);

    @POST
    @Path("postbytearray")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response postByteArray(@Context HttpHeaders headers, byte[] array);

    @GET
    @Path("getboolean")
    public Response getBoolean(@Context HttpHeaders headers);

    @POST
    @Path("boolean")
    @Consumes(MediaType.TEXT_PLAIN)
    public Object postBoolean(Boolean bool);

    @GET
    @Path("getchar")
    public Response getChar(@Context HttpHeaders headers);

    @POST
    @Path("postchar")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response postChar(@Context HttpHeaders headers, char c);

}
