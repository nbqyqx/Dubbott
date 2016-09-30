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

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.ws.rs.Path;
import javax.ws.rs.client.InvocationCallback;

import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.ThreadLocalClientState;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.remoting.http.HttpBinder;
import com.alibaba.dubbo.remoting.http.servlet.BootstrapListener;
import com.alibaba.dubbo.remoting.http.servlet.DispatcherServlet;
import com.alibaba.dubbo.remoting.http.servlet.ServletManager;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.ServiceClassHolder;
import com.alibaba.dubbo.rpc.protocol.AbstractProxyProtocol;

/**
 * CXF Rest Protocol support
 * 
 * @author lizhe on 2016.6.15 10:29:55 AM
 */
public class CXFRestProtocol extends AbstractProxyProtocol {

    private static final int              DEFAULT_PORT  = 80;
    private final Map<String, RestServer> servers       = new ConcurrentHashMap<String, RestServer>();
    private final CXFRestServerFactory    serverFactory = new CXFRestServerFactory();

    public void setHttpBinder(HttpBinder httpBinder) {
        serverFactory.setHttpBinder(httpBinder);
    }

    @Override
    protected <T> Runnable doExport(T impl, Class<T> type, URL url) throws RpcException {
        String addr = url.getIp() + ":" + url.getPort();
        Class<?> implClass = ServiceClassHolder.getInstance().popServiceClass();
        String contextPath = getContextPath(url);
        RestServer server = servers.get(addr);
        if (server == null) {
            server = serverFactory.createServer(url.getParameter(Constants.SERVER_KEY, "servlet"));
            server.start(url);
            servers.put(addr, server);

            // if dubbo consumer doesn't use the contextPath, then we can remove this check.
            if ("servlet".equalsIgnoreCase(url.getParameter(Constants.SERVER_KEY, "servlet"))) {
                ServletContext servletContext = ServletManager.getInstance().getServletContext(ServletManager.EXTERNAL_SERVER_PORT);
                
                if (servletContext == null) {
                    throw new RpcException("No servlet context found. Since you are using server='servlet', "
                                           + "make sure that you've configured " + BootstrapListener.class.getName()
                                           + " in web.xml");
                }

                this.validateContextPath(contextPath, servletContext);

                servletContext.setAttribute(CXFRestConstants.CONTEXT_PATH, "/" + contextPath);
                servletContext.setAttribute(CXFRestConstants.DUBBOTT_URL, url);
            }
        }

        final Class<?> resourceDef = getRootResourceClass(implClass) != null ? implClass : type;
        server.deploy(type, impl, null);
        // server.deploy(resourceDef, impl, contextPath);

        final RestServer s = server;
        return new Runnable() {

            public void run() {
                s.undeploy(resourceDef);
            }
        };
    }

    public void validateContextPath(String contextPath, ServletContext servletContext) {
        String dubboServletMappingPath = contextPath;
        String webappPath = servletContext.getContextPath();
        if (!StringUtils.isBlank(webappPath)) {
            webappPath = webappPath.substring(1);
            if (!contextPath.startsWith(webappPath)) {
                throw new RpcException("Make sure that the 'contextpath' property starts with the path of external webapp, since you are using server='servlet'.");
            }
            dubboServletMappingPath = contextPath.substring(webappPath.length());
        }

        ServletRegistration servletRegistration = null;
        Map<String, ? extends ServletRegistration> servletRegistrations = servletContext.getServletRegistrations();
        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration value : values) {
            if (DispatcherServlet.class.getName().equals(value.getClassName())) {
                servletRegistration = value;
                break; // dubbo consumer只能接受一个URL Mapping
            }
        }

        if (servletRegistration == null) {
            throw new RpcException("Make sure that you've configured " + DispatcherServlet.class.getName()
                                   + " in web.xml, since you are using server='servlet' ");
        }

        Collection<String> urlMappings = servletRegistration.getMappings();
        if (urlMappings == null || urlMappings.isEmpty()) {
            throw new RpcException("No servlet-mapping found for the servlet "
                                   + DispatcherServlet.class.getName()
                                   + ", since you are using server='servlet' ");
        }

        if (!dubboServletMappingPath.startsWith("/")) {
            dubboServletMappingPath = "/" + dubboServletMappingPath;
        }

        for (String mappingUrl : urlMappings) {
            if (!mappingUrl.startsWith(dubboServletMappingPath)) { // contextPath.matches(mappingUrl)
                throw new RpcException("The servlet mappingUrl in web.xml doesn't match with the 'contextpath' property, since you are using server='servlet'."
                                       + "contextPath: " + contextPath + ", servletmappingUrl: " + mappingUrl);
            }
        }
    }

    @Override
    protected <T> T doRefer(Class<T> type, URL url) throws RpcException {
        // add client side extension support. filter interceptor provider and properties(timeout)
        long timeToKeepState = 0L; // how long to keep this state ;
        List<Object> providers = new ArrayList<Object>();
        List<Feature> features = new ArrayList<Feature>();
        this.loadClientProvidersAndFeature(providers, features);
        StringBuilder buf = new StringBuilder(128);
        // "http://" + url.getHost() + ":" + url.getPort() + "/" + getContextPath(url)
        buf.append("http://").append(url.getHost()).append(":").append(url.getPort()).append("/").append(getContextPath(url));
        String baseAddress = buf.toString();
        JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
        bean.setAddress(baseAddress);
        bean.setServiceClass(type);
        bean.setProviders(providers);
        bean.setFeatures(features);
        bean.setInitialState(new ThreadLocalClientState(baseAddress, timeToKeepState));
        bean.setThreadSafe(true);
        T proxy = bean.create(type);
        this.setTimeout(url, proxy);
        String callback = url.getParameter("callback");
        if(callback != null && !callback.trim().isEmpty() ){
            WebClient.getConfig(proxy).getRequestContext().put(InvocationCallback.class.getName(), loadCallbacks(callback.trim()));
        }
        
        return proxy;
    }
    
    private List<InvocationCallback<?>> loadCallbacks(String value) {
        List<InvocationCallback<?>>  callbacks = new ArrayList<InvocationCallback<?>>();
        
        for (String clazz : Constants.COMMA_SPLIT_PATTERN.split(value)) {
            if (!StringUtils.isEmpty(clazz)) {
                try {
                    callbacks.add((InvocationCallback<?>) Class.forName(clazz.trim()).newInstance());
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return callbacks;
    }

    private <T> void setTimeout(URL url, T proxy) {
        int connectionTimeout = url.getParameter(Constants.CONNECT_TIMEOUT_KEY, CXFRestConstants.DEFAULT_TIMEOUT);
        int soTimeout = url.getParameter(Constants.TIMEOUT_KEY, CXFRestConstants.DEFAULT_TIMEOUT);
        ClientConfiguration config = WebClient.getConfig(proxy);
        HTTPConduit httpConduit = config.getHttpConduit();
        HTTPClientPolicy httpClientPolicy = httpConduit.getClient();
        httpClientPolicy.setConnectionTimeout(connectionTimeout);
        httpClientPolicy.setReceiveTimeout(soTimeout);
    }

    private void loadClientProvidersAndFeature(List<Object> providers, List<Feature> features) {
        List<Object> extensions = CXFRestClientExtensionsFactory.getCXFRestClientExtensions();
        if (extensions != null && !extensions.isEmpty()) {
            for (Object extension : extensions) {
                Class<?> clazz = extension.getClass();
                /**
                 * check if the class is a valid provider
                 */
                if (ResourceUtils.isValidProvider(clazz, false)) {
                    providers.add(extension);
                } else if (Feature.class.isAssignableFrom(clazz)) {
                    features.add((Feature) extension);
                }
            }
        }
    }

    @Override
    public int getDefaultPort() {
        return DEFAULT_PORT;
    }

    public static Class<?> getRootResourceClass(Class<?> clazz) {
        return getClassWithAnnotation(clazz, Path.class);
    }

    public static Class<?> getClassWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
        if (clazz.isAnnotationPresent(annotation)) {
            return clazz;
        }
        for (Class<?> intf : clazz.getInterfaces()) {
            if (intf.isAnnotationPresent(annotation)) {
                return intf;
            }
        }
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != Object.class && superClass != null) {
            return getClassWithAnnotation(superClass, annotation);
        }
        return null;

    }

    protected String getContextPath(URL url) {
        int pos = url.getPath().lastIndexOf("/");
        return pos > 0 ? url.getPath().substring(0, pos) : "";
    }

}
