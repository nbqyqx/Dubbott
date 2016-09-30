package com.tops001.dubbott;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.protocol.rest.cxf.CXFRestProtocol;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ServletContext.class, ServletRegistration.class })
public class CXFRestProtocolTest {

    private CXFRestProtocol cxfRestProtocol = new CXFRestProtocol();
    private ServletContext  servletContext;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testOK_contextPath_isNotEmpty() {
        String contextPath = "dubbott-demo-provider/v1.0";
        servletContext = PowerMock.createMock(ServletContext.class);
        EasyMock.expect(servletContext.getContextPath()).andReturn("/dubbott-demo-provider");
        ServletRegistration servletRegistrationMock = PowerMock.createMock(ServletRegistration.class);
        Map result = new HashMap();
        result.put("aServerlet", servletRegistrationMock);
        EasyMock.expect(servletContext.getServletRegistrations()).andReturn(result);
        EasyMock.expect(servletRegistrationMock.getClassName()).andReturn("com.alibaba.dubbo.remoting.http.servlet.DispatcherServlet");
        Collection<String> urlMappings = new ArrayList<String>();
        urlMappings.add("/v1.0/*");
        EasyMock.expect(servletRegistrationMock.getMappings()).andReturn(urlMappings);
        PowerMock.replayAll();
        this.cxfRestProtocol.validateContextPath(contextPath, servletContext);
        PowerMock.verifyAll();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testOK_contextPath_isEmpty() {
        String contextPath = "v1.0";
        servletContext = PowerMock.createMock(ServletContext.class);
        EasyMock.expect(servletContext.getContextPath()).andReturn("");
        ServletRegistration servletRegistrationMock = PowerMock.createMock(ServletRegistration.class);
        Map result = new HashMap();
        result.put("aServerlet", servletRegistrationMock);
        EasyMock.expect(servletContext.getServletRegistrations()).andReturn(result);
        EasyMock.expect(servletRegistrationMock.getClassName()).andReturn("com.alibaba.dubbo.remoting.http.servlet.DispatcherServlet");
        Collection<String> urlMappings = new ArrayList<String>();
        urlMappings.add("/v1.0/*");
        EasyMock.expect(servletRegistrationMock.getMappings()).andReturn(urlMappings);
        PowerMock.replayAll();
        this.cxfRestProtocol.validateContextPath(contextPath, servletContext);
        PowerMock.verifyAll();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test(expected = RpcException.class)
    public void testException_contextPath_isNotMatch() {
        String contextPath = "errorPath/v1.0";
        servletContext = PowerMock.createMock(ServletContext.class);
        EasyMock.expect(servletContext.getContextPath()).andReturn("/dubbott-demo-provider");
        ServletRegistration servletRegistrationMock = PowerMock.createMock(ServletRegistration.class);
        Map result = new HashMap();
        result.put("aServerlet", servletRegistrationMock);
        EasyMock.expect(servletContext.getServletRegistrations()).andReturn(result);
        EasyMock.expect(servletRegistrationMock.getClassName()).andReturn("com.alibaba.dubbo.remoting.http.servlet.DispatcherServlet");
        Collection<String> urlMappings = new ArrayList<String>();
        urlMappings.add("/v1.0/*");
        EasyMock.expect(servletRegistrationMock.getMappings()).andReturn(urlMappings);
        PowerMock.replayAll();
        this.cxfRestProtocol.validateContextPath(contextPath, servletContext);
        PowerMock.verifyAll();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test(expected = RpcException.class)
    public void testException_dubboServlet_isNotDefine() {
        String contextPath = "dubbott-demo-provider/v1.0";
        servletContext = PowerMock.createMock(ServletContext.class);
        EasyMock.expect(servletContext.getContextPath()).andReturn("/dubbott-demo-provider");
        Map result = new HashMap();
        EasyMock.expect(servletContext.getServletRegistrations()).andReturn(result);
        PowerMock.replayAll();
        this.cxfRestProtocol.validateContextPath(contextPath, servletContext);
        PowerMock.verifyAll();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test(expected = RpcException.class)
    public void testException_urlMappings_isNotDefine() {
        String contextPath = "errorPath/v1.0";
        servletContext = PowerMock.createMock(ServletContext.class);
        EasyMock.expect(servletContext.getContextPath()).andReturn("/dubbott-demo-provider");
        ServletRegistration servletRegistrationMock = PowerMock.createMock(ServletRegistration.class);
        Map result = new HashMap();
        result.put("aServerlet", servletRegistrationMock);
        EasyMock.expect(servletContext.getServletRegistrations()).andReturn(result);
        EasyMock.expect(servletRegistrationMock.getClassName()).andReturn("com.alibaba.dubbo.remoting.http.servlet.DispatcherServlet");
        Collection<String> urlMappings = new ArrayList<String>();
        EasyMock.expect(servletRegistrationMock.getMappings()).andReturn(urlMappings);
        PowerMock.replayAll();
        this.cxfRestProtocol.validateContextPath(contextPath, servletContext);
        PowerMock.verifyAll();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test(expected = RpcException.class)
    public void testException_urlMappings_isNotMatch() {
        String contextPath = "dubbott-demo-provider/v1.0";
        servletContext = PowerMock.createMock(ServletContext.class);
        EasyMock.expect(servletContext.getContextPath()).andReturn("/dubbott-demo-provider");
        ServletRegistration servletRegistrationMock = PowerMock.createMock(ServletRegistration.class);
        Map result = new HashMap();
        result.put("aServerlet", servletRegistrationMock);
        EasyMock.expect(servletContext.getServletRegistrations()).andReturn(result);
        EasyMock.expect(servletRegistrationMock.getClassName()).andReturn("com.alibaba.dubbo.remoting.http.servlet.DispatcherServlet");
        Collection<String> urlMappings = new ArrayList<String>();
        urlMappings.add("/v2.0/*");
        EasyMock.expect(servletRegistrationMock.getMappings()).andReturn(urlMappings);
        PowerMock.replayAll();
        this.cxfRestProtocol.validateContextPath(contextPath, servletContext);
        PowerMock.verifyAll();
    }

}
