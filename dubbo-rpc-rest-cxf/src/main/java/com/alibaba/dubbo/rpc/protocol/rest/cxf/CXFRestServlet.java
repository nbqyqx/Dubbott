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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

/**
 * CXF Rest Servlet Wrapper
 * 
 * @author lizhe on 2016.6.15 11:04:49 AM
 */
public class CXFRestServlet extends CXFNonSpringServlet {

    private static final long serialVersionUID = 1719962638804785505L;
    private CXFDeployment cxfDeployment;    
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        this.cxfDeployment = new CXFDeployment(Thread.currentThread().getContextClassLoader());        

        JAXRSServerFactoryBean jaxRsServerFactory = new JAXRSServerFactoryBean();
        jaxRsServerFactory.setBus(getBus());
        String address = "/";
        jaxRsServerFactory.setAddress(address);
        jaxRsServerFactory.setStaticSubresourceResolution(false);
        servletConfig.getServletContext().setAttribute(JAXRSServerFactoryBean.class.getName(), jaxRsServerFactory);
        servletConfig.getServletContext().setAttribute(CXFDeployment.class.getName(), this.cxfDeployment);
        //servletConfig.getServletContext().setAttribute(CXFRestProtocol.CONTEXT_PATH, getAttribute+ address);
    }
    
    public CXFDeployment getCxfDeployment() {
        return cxfDeployment;
    }
    
    public void setCxfDeployment(CXFDeployment cxfDeployment) {
        this.cxfDeployment = cxfDeployment;
    }

}
