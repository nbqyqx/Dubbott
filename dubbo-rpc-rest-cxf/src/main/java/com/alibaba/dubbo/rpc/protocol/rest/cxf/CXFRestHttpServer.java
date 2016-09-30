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

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.remoting.http.HttpBinder;
import com.alibaba.dubbo.remoting.http.HttpHandler;
import com.alibaba.dubbo.remoting.http.HttpServer;
import com.alibaba.dubbo.remoting.http.servlet.BootstrapListener;
import com.alibaba.dubbo.remoting.http.servlet.ServletManager;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * @author lizhe on 2016.6.15 6:36:25 PM
 */
public class CXFRestHttpServer extends BaseRestServer {

    private CXFRestServlet cxfRestServlet;
    private HttpBinder     httpBinder;
    private HttpServer     httpServer;

    public CXFRestHttpServer(HttpBinder httpBinder){
        this.httpBinder = httpBinder;
        cxfRestServlet = new CXFRestServlet();
    }

    protected void doStart(URL url) {
        httpServer = httpBinder.bind(url, new CXFRestHandler());

        ServletContext sc = ServletManager.getInstance().getServletContext(url.getPort());
        if (sc == null) {
            sc = ServletManager.getInstance().getServletContext(ServletManager.EXTERNAL_SERVER_PORT);
        }
        if (sc == null) {
            throw new RpcException("No servlet context found. If you are using server='servlet', "
                                   + "make sure that you've configured " + BootstrapListener.class.getName()
                                   + " in web.xml");
        }
       
        // init CXFRestServlet
        ServletConfig servletConfig = getServletConfig(sc);
        
        try {
            cxfRestServlet.init(servletConfig);
        } catch (ServletException e) {
            throw new RpcException(e);
        }
    }

    private ServletConfig getServletConfig(final ServletContext servletContext) {
        ServletConfig servletConfig = new ServletConfig() {

            @Override
            public String getServletName() {
                return "DispatcherServlet";
            }

            @Override
            public ServletContext getServletContext() {
                return servletContext;
            }

            @Override
            public Enumeration<String> getInitParameterNames() {
                return null;
            }

            @Override
            public String getInitParameter(String name) {
                return null;
            }
        };
        return servletConfig;
    }

    public void stop() {
        httpServer.close();
    }

    protected CXFDeployment getDeployment() {
        return this.cxfRestServlet.getCxfDeployment();
    }

    private class CXFRestHandler implements HttpHandler {

        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException,
                                                                                     ServletException {
            cxfRestServlet.service(request, response);
        }
    }

}
