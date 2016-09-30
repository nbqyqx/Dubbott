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

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * CXF JAXRSServer create listener
 *
 * @author Neal Hu on 2016.6.15 5:02:54 PM
 */
public class CXFServerLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        CXFDeployment cxfDeployment = (CXFDeployment) sce.getServletContext().getAttribute(CXFDeployment.class.getName());
        JAXRSServerFactoryBean jaxRsServerFactory = (JAXRSServerFactoryBean) sce.getServletContext().getAttribute(JAXRSServerFactoryBean.class.getName());
        CXFJaxRsProviderResourceHolder cxfPRHolder = cxfDeployment.getCxfPRHolder();
        String swaggerBasePath = (String) sce.getServletContext().getAttribute(CXFRestConstants.CONTEXT_PATH);
        URL dubbottUrl = (URL) sce.getServletContext().getAttribute(CXFRestConstants.DUBBOTT_URL);
        // bean.setBus(getBus());

        // String address = servletConfig.getInitParameter("jaxrs.address");
        // if (address == null) {
        // address = "/";
        // }
        // bean.setAddress(address);
        //
        // bean.setStaticSubresourceResolution(getStaticSubResolutionValue(servletConfig));
        //
        // String modelRef = servletConfig.getInitParameter("user.model");
        // if (modelRef != null) {
        // bean.setModelRef(modelRef.trim());
        // }
        // setDocLocation(bean, servletConfig);
        // setSchemasLocations(bean, servletConfig);
        // setAllInterceptors(bean, servletConfig, splitChar);
        // setInvoker(bean, servletConfig); //add bean validation support for per request service --//to be add
        jaxRsServerFactory.setResourceClasses(cxfPRHolder.getResourceClasses());
        setJacksonProvider(cxfPRHolder.getProviders());
        jaxRsServerFactory.setProviders(cxfPRHolder.getProviders());
        for (Map.Entry<Class<?>, ResourceProvider> entry : cxfPRHolder.getResouceProviderMap().entrySet()) {
            jaxRsServerFactory.setResourceProvider(entry.getKey(), entry.getValue());
        }
        setSwaggerFeature(swaggerBasePath, dubbottUrl, cxfPRHolder.getFeatures());
        jaxRsServerFactory.setFeatures(cxfPRHolder.getFeatures());
        jaxRsServerFactory.create();
    }

    private void setJacksonProvider(List<Object> providers) {
        for (Object p : providers) {
            if (JacksonJsonProvider.class.isAssignableFrom( p.getClass() )) {
                JacksonJsonProvider provider = (JacksonJsonProvider) p;
                ObjectMapper mapperObject = new ObjectMapper();
                // AnnotationIntrospector annotationIntrospectorPairObject = new AnnotationIntrospector.Pair(new
                // JaxbAnnotationIntrospector(), new JacksonAnnotationIntrospector());

                // SerializationConfig serializationConfig = mapperObject.getSerializationConfig();
                // serializationConfig.withSerializationInclusion(JsonInclude.Include);
                // serializationConfig.setAnnotationIntrospector(annotationIntrospectorPairObject);

                // DeserializationConfig deserializationConfig = mapperObject.getDeserializationConfig();
                // deserializationConfig.setAnnotationIntrospector(annotationIntrospectorPairObject);
                //mapperObject.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);
                mapperObject.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
                mapperObject.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                //mapperObject.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);
                
                provider.setMapper(mapperObject);
            }
        }

    }

    private void setSwaggerFeature(String basePath, URL url, List<Feature> features) {
        for (Feature f : features) {
            if (f instanceof Swagger2Feature) {
                Swagger2Feature feature = (Swagger2Feature) f;
                feature.setBasePath(basePath);
                feature.setTitle(url.getParameter(Constants.APPLICATION_KEY, "Tops Tech Rest Application"));
                feature.setDescription("Tops Tech Rest Application");
                feature.setContact(url.getParameter(CXFRestConstants.OWNER_KEY));
                feature.setVersion("See BASE PATH");
                // feature.setLicense("Tops Tech License");
                // feature.setLicenseUrl(licenseUrl);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        JAXRSServerFactoryBean jaxRsServerFactory = (JAXRSServerFactoryBean) sce.getServletContext().getAttribute(JAXRSServerFactoryBean.class.getName());
        jaxRsServerFactory.getServer().destroy();
    }

}
