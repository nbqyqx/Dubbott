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
package com.alibaba.dubbo.rpc.protocol.rest.cxf;

import com.alibaba.dubbo.remoting.http.HttpBinder;

/**
 * CXF Rest web service factory
 * 
 * @author lizhe on 2016.6.15 6:37:18 PM
 */
public class CXFRestServerFactory {

    private HttpBinder httpBinder;

    public void setHttpBinder(HttpBinder httpBinder) {
        this.httpBinder = httpBinder;
    }

    public RestServer createServer(String name) {
        if ("servlet".equalsIgnoreCase(name) /* || "jetty".equalsIgnoreCase(name) || "tomcat".equalsIgnoreCase(name) */) {
            return new CXFRestHttpServer(httpBinder);
        } else if ("netty".equalsIgnoreCase(name)) {
            // return new NettyServer();
            throw new IllegalArgumentException("UnSupport server name: " + name);
        } else if ("sunhttp".equalsIgnoreCase(name)) {
            // return new SunHttpServer();
            throw new IllegalArgumentException("UnSupport server name: " + name);
        } else {
            throw new IllegalArgumentException("UnSupport server name: " + name);
        }
    }
}
