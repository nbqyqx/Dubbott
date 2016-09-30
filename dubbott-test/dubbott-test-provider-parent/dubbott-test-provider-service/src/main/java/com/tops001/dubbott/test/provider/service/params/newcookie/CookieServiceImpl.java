package com.tops001.dubbott.test.provider.service.params.newcookie;

import java.util.Map;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.params.newcookie.CookieService;

@Service
public class CookieServiceImpl implements CookieService {

    private UriInfo uriInfo;

    private String value3;
    
    public void setUriInfo(UriInfo uriInfo) {
    	this.uriInfo = uriInfo;
    }

    public Response getCookie(HttpHeaders headers) {
        Map<String, Cookie> cookies = headers.getCookies();
        String ret = "";
        if (cookies != null) {
            for (String s : cookies.keySet()) {
                Cookie c = cookies.get(s);
                ret +=
                                c.getName() + ","
                                                + c.getValue()
                                                + ","
                                                + c.getPath()
                                                + ","
                                                + c.getDomain().toLowerCase()
                                                + "\r";
            }
        }
        return Response.ok(ret).build();
    }

    public Response getValue2() {
        return Response.status(Status.BAD_REQUEST).entity("value2").build();
        // return
        // Response.status(Status.BAD_REQUEST).entity(this.value2).build();
        // return Response.ok(this.value2).build();
    }

    public Response getStaticValue() {
        return null;
        // return Response.ok(value).build();
    }

    public Response getValue3() {
        return Response.status(Status.BAD_REQUEST).entity(this.value3).build();
        // return Response.ok(this.value3).build();
    }

    public Response setCookies() {
        ResponseBuilder rb = Response.ok();
        rb.cookie(new NewCookie("name", "value", uriInfo.getBaseUri().getPath() + uriInfo.getPath(), uriInfo
                        .getBaseUri().getHost().toLowerCase(), "comment", 10, false));
        rb.cookie(new NewCookie("name2", "value2", uriInfo.getBaseUri().getPath() + uriInfo.getPath(), uriInfo
                        .getBaseUri().getHost().toLowerCase(), "comment2", 10, false));
        rb.cookie(new NewCookie("name3", "value3", uriInfo.getBaseUri().getPath() + uriInfo.getPath(), uriInfo
                        .getBaseUri().getHost().toLowerCase(), "comment2", 10, false));
        return rb.build();
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }
}
