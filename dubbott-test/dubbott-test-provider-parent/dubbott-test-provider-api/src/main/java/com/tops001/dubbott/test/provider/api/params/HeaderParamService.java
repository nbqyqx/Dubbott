package com.tops001.dubbott.test.provider.api.params;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Resource with<code>HeaderParam</code>.
 * 
 * @see HeaderParam
 */
@Path("header")
public interface HeaderParamService {

	@HeaderParam("Accept-Language")
    public void setAcceptLanguage(String acceptLanguage);

    static public class HeaderValueOf {
        private HeaderValueOf(String somevalue) {}

        public static HeaderValueOf valueOf(String someValue) {
            if ("throwex".equals(someValue)) {
                throw new WebApplicationException(499);
            } else if ("throwruntimeex".equals(someValue)) {
                throw new IllegalArgumentException();
            }
            return new HeaderValueOf(someValue);
        }
    }

    static public class HeaderConstructor {
        public HeaderConstructor(String somevalue) {
            if ("throwex".equals(somevalue)) {
                throw new WebApplicationException(499);
            } else if ("throwruntimeex".equals(somevalue)) {
                throw new IllegalArgumentException();
            }
        }
    }

    @HeaderParam("customHeaderParam")
    public void setCstrHeaderParam(String cstrHeaderParam);

    @GET
    public Response getHeaderParam(@HeaderParam("Accept-Language") String methodLanguage);

    @POST
    public Response getHeaderParamPost(@HeaderParam("CustomHeader") HeaderValueOf customHeader,
                                       @HeaderParam("CustomConstructorHeader") HeaderConstructor customHeader2);

    @HeaderParam("User-Agent")
    public void setUserAgent(String aUserAgent);

    public String getUserAgent();
    
}
