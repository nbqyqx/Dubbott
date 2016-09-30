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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;

/**
 *
 * @author Neal Hu on 2016.6.15 5:02:54 PM
 */
public class CXFJaxRsProviderResourceHolder {

    List<Class<?>>                  resourceClasses     = new ArrayList<Class<?>>();
    List<Object>                    providers           = new ArrayList<Object>();
    Map<Class<?>, ResourceProvider> resouceProviderMap  = new HashMap<Class<?>, ResourceProvider>();
    Map<Class<?>, Class<?>>         abstractResourceMap = new HashMap<Class<?>, Class<?>>();
    List<Feature>                   features            = new ArrayList<Feature>();
   
    public void addAbstractResourceMapItem(Class<?> abstractInterfaceClass, Class<?> concreteClass) {
        abstractResourceMap.put(abstractInterfaceClass, concreteClass);
    }

    public Map<Class<?>, Class<?>> getAbstractResourceMap() {
        return abstractResourceMap;
    }

    public List<Class<?>> getResourceClasses() {
        return resourceClasses;
    }

    public void addResourceClasses(Class<?> resourceClass) {
        this.resourceClasses.add(resourceClass);
    }

    public void removeResourceClasses(Class<?> resourceClass) {
        this.resourceClasses.remove(resourceClass);
    }

    public List<Object> getProviders() {
        return providers;
    }

    public void addProvider(Object provider) {
        this.providers.add(provider);
    }
    
    
    public List<Feature> getFeatures() {
        return features;
    }

    
    public void addFeature(Feature feature) {
        this.features.add(feature);
    }


    public Map<Class<?>, ResourceProvider> getResouceProviderMap() {
        return resouceProviderMap;
    }

    public boolean addResouceProvider(Class<?> c, ResourceProvider rp) {
        if (this.resouceProviderMap.containsKey(c)) {
            return true;
        }
        this.resouceProviderMap.put(c, rp);
        return false;
    }

    public boolean removeResouceProvider(Class<?> c) {
        if (!this.resouceProviderMap.containsKey(c)) {
            return false;
        }
        this.resouceProviderMap.remove(c);
        return true;
    }
}
