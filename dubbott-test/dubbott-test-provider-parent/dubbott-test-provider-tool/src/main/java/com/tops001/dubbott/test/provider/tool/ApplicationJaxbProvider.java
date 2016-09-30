package com.tops001.dubbott.test.provider.tool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBElement;

@Provider
public class ApplicationJaxbProvider
                implements MessageBodyReader<JAXBElement<String>>, MessageBodyWriter<JAXBElement<String>>
{
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
    {
        return JAXBElement.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(JAXBElement<String> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
    {
        return t.getValue().length() + 2;
    }

    @Override
    public void writeTo(JAXBElement<String> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream)
                    throws IOException, WebApplicationException
    {
        System.out.println("AppJaxbProvider.writeTo is working now");
        entityStream.write(t.getValue().getBytes());
        entityStream.write("OK".getBytes());
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
    {
        return isWriteable(type, genericType, annotations, mediaType);
    }

    @Override
    public JAXBElement<String> readFrom(Class<JAXBElement<String>> type, Type genericType, Annotation[] annotations, MediaType mediaType,
                                        MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
                    throws IOException, WebApplicationException
    {
        System.out.println("AppJaxbProvider.readFrom is working now");
        return null;
    }
}