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

import java.lang.reflect.Constructor;

import javax.servlet.ServletException;

import org.apache.cxf.common.classloader.ClassLoaderUtils;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;



/**
 * @author lizhe on 2016.6.15 5:25:24 PM
 */
public class CXFDeployment {

    private ClassLoader    classLoader;
    private CXFJaxRsProviderResourceHolder cxfPRHolder;
    
    public CXFJaxRsProviderResourceHolder getCxfPRHolder() {
        return cxfPRHolder;
    }

    public CXFDeployment(ClassLoader classLoader){
        super();
        this.classLoader = classLoader;
        this.cxfPRHolder = new CXFJaxRsProviderResourceHolder();
    }

    /**
     * add ResourceClass
     *
     * @param serviceClass
     */
    public void addResourceClass(Class<?> serviceClass, Object serviceObject) {
        
        /**
         * check if the class is a valid resource (class with @Path)
         * in case of the registration to some invalid providers
         */
        if (ResourceUtils.isValidResource(serviceClass)) {
            if (cxfPRHolder.addResouceProvider(serviceClass,new SingletonResourceProvider(serviceObject)) == false) {
                cxfPRHolder.addResourceClasses(serviceClass);
            }
        }
       
    }

    /**
     * add Provider
     * 
     * @param provider
     * @throws ServletException
     */
    public void addProvider(String providerName) {
        
        Class<?> clazz = null;
        try {
            clazz = loadClass(providerName);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        
        //boolean isJaxRsProvider = false;
        Object singletonProviderInstance = null; 
        /**
         * check if the class is a valid provider
         */        
        if (ResourceUtils.isValidProvider(clazz)) {
            //isJaxRsProvider = true;
            try {
                Constructor<?> c = ResourceUtils.findResourceConstructor(clazz, false);
                if (c.getParameterTypes().length == 0) {
                    singletonProviderInstance = c.newInstance();
                    cxfPRHolder.addProvider(singletonProviderInstance);
                } else {
                    cxfPRHolder.addProvider(c);
                }

            } catch (Throwable ex) {
                throw new RuntimeException("Provider " + clazz.getName() + " can not be created", ex);
            }
        } else if(Feature.class.isAssignableFrom(clazz)) {
            
            try {
                Constructor<?> c = ResourceUtils.findResourceConstructor(clazz, false);
                if (c.getParameterTypes().length == 0) {
                    singletonProviderInstance = c.newInstance();
                    cxfPRHolder.addFeature((Feature)singletonProviderInstance);
                } else {
                    //cxfPRHolder.addFeature((Feature)c);
                }

            } catch (Throwable ex) {
                throw new RuntimeException("Feature " + clazz.getName() + " can not be created", ex);
            }
            
        }
    }
    


    protected Class<?> loadClass(String cName) throws ServletException {
        return loadClass(cName, "Resource");
    }

    protected Class<?> loadClass(String cName, String classType) throws ServletException {
        try {
            Class<?> cls = null;
            if (classLoader == null) {
                cls = ClassLoaderUtils.loadClass(cName, CXFRestProtocol.class);
            } else {
                cls = classLoader.loadClass(cName);
            }
            return cls;
        } catch (ClassNotFoundException ex) {
            throw new ServletException("No " + classType + " class " + cName.trim() + " can be found", ex);
        }
    }

    public void removeResourceClass(@SuppressWarnings("rawtypes") Class resourceDef) {
        // TODO Auto-generated method stub

    }
}
