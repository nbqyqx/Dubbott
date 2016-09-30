package com.tops001.dubbott.test.provider.tool;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

@Provider
@Consumes("text/plain")
@Produces("text/plain")
public class ApplicationCharClientProvider implements MessageBodyReader<Character> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return Character.class.isAssignableFrom(type);
	}

	@Override
	public Character readFrom(Class<Character> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
					throws IOException, WebApplicationException {
		ObjectInputStream ois = new ObjectInputStream(entityStream);
		try {
			Character character = (Character)ois.readObject();
			return character;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
