package com.tops001.dubbott.test.consumer.service;

import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;
import javax.ws.rs.core.Variant.VariantListBuilder;

import org.springframework.stereotype.Service;

import org.json.JSONObject;
import com.tops001.dubbott.test.consumer.api.EchoService;
import com.tops001.dubbott.test.consumer.model.Echo;

@Service
public class EchoServiceImpl implements EchoService {

    public Response getAcceptHeaderEcho(HttpHeaders requestHeaders, Request request) throws Exception {
        StringBuffer sb = new StringBuffer("echo: ");
        List<String> acceptHeader = requestHeaders.getRequestHeader(HttpHeaders.ACCEPT);

        if (acceptHeader != null) {
            for (String s : acceptHeader) {
                sb.append(s);
            }
        }

        if (acceptHeader == null || acceptHeader.isEmpty()
            || MediaType.WILDCARD_TYPE.equals(requestHeaders.getAcceptableMediaTypes().get(0))) {
            return Response.ok(sb.toString()).type(MediaType.TEXT_PLAIN_TYPE).build();
        }

        Variant variant =
                        request.selectVariant(VariantListBuilder.newInstance()
                                        .mediaTypes(MediaType.TEXT_PLAIN_TYPE,
                                                    MediaType.TEXT_XML_TYPE,
                                                    MediaType.APPLICATION_JSON_TYPE).add().build());
        if (variant != null) {
            if (MediaType.APPLICATION_JSON_TYPE.isCompatible(variant.getMediaType())) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("value", sb.toString());
                return Response.ok(jsonObject).type(MediaType.APPLICATION_JSON).build();
            } else if (MediaType.TEXT_XML_TYPE.isCompatible(variant.getMediaType())) {
                Echo e = new Echo();
                e.setValue(sb.toString());
                return Response.ok(e).type(MediaType.TEXT_XML).build();
            }
        }

        return Response.ok(sb.toString()).type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
