package com.tops001.dubbott.test.provider.service.readerwritermatch;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;
import javax.xml.transform.Source;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.readerwritermatch.ReaderWriterMatchService;

@Service
public class ReaderWriterMatchServiceImpl implements ReaderWriterMatchService {

	@Override
	public Source source(Source source) {
		return source;
	}

	@Override
	public JAXBElement<String> jaxb(JAXBElement<String> jaxb) {
		return jaxb;
	}

	@Override
	public MultivaluedMap<String, String> map(MultivaluedMap<String, String> map) {
		return map;
	}

	@Override
	public Character character(Character character) {
		if (character != 'a')
            throw new WebApplicationException(Status.NOT_ACCEPTABLE);
        return character;
	}

	@Override
	public String geText() {
		return "text/* is ok";
	}

	@Override
	public Boolean bool(Boolean bool) {
		if (bool)
            throw new WebApplicationException(Status.NOT_ACCEPTABLE);
        return false;
	}

	@Override
	public Response postByteArray(HttpHeaders headers, byte[] array) {
		return buildResponse(headers, new String(array));
	}

	@Override
	public Response getBoolean(HttpHeaders headers) {
		Boolean b = false;
        return buildResponse(headers, b, MediaType.TEXT_PLAIN_TYPE);
	}

	@Override
	public Object postBoolean(Boolean bool) {
		if (bool) {
            System.out.println("=================406=============");
            throw new WebApplicationException(Status.NOT_ACCEPTABLE);
        }
        System.out.println("=================200=============");
        return false;
	}

	@Override
	public Response getChar(HttpHeaders headers) {
		Character c = 'R';
        return buildResponse(headers, c, MediaType.TEXT_PLAIN_TYPE);
	}

	@Override
	public Response postChar(HttpHeaders headers, char c) {
		String text = String.valueOf(c);
        return buildResponse(headers, text);
	}
	
	private Response buildResponse(HttpHeaders headers, Object content) {
        return buildResponse(headers, content, MediaType.WILDCARD_TYPE);
    }

    private Response buildResponse(HttpHeaders headers, Object content, MediaType type) {
        List<String> list = headers.getRequestHeader(HEADERNAME);
        String name = null;
        if (list != null && list.size() != 0)
            name = list.iterator().next();
        ResponseBuilder builder = Response.ok(content, type).type(type);
        if (name != null)
            builder.header(HEADERNAME, name + DIRECTION);
        return builder.build();
    }
	
	public static final String HEADERNAME = "FILTER_HEADER";
    public static final String DIRECTION = "FROM_RESOURCE";

}
