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
import java.util.List;

import org.apache.cxf.feature.Feature;

import com.alibaba.dubbo.common.extension.ExtensionFactory;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * @author LiZhe on Jul 6, 2016 5:17:26 PM
 */
public class CXFRestClientExtensionsFactory {

    public static List<Object> getCXFRestClientExtensions() {
        ExtensionLoader<ExtensionFactory> extensionLoader = ExtensionLoader.getExtensionLoader(ExtensionFactory.class);
        ExtensionFactory extensionFactory = extensionLoader.getLoadedExtension("spring");
        CXFRestClientExtensions extension = extensionFactory.getExtension(CXFRestClientExtensions.class,
                                                                          "cxfRestClientExtensions");
        if (extension != null) {
            return extension.getExtensions();
        }

        return getDefaultProvider();
    }

    public static List<Object> getDefaultProvider() {
        return DefaultExtensionsHolder.extensions;
    }

    private static class DefaultExtensionsHolder {

        private static String[]    extensionClass = { "com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider" };

        public static List<Object> extensions     = new ArrayList<Object>() {

                                                      private static final long serialVersionUID = -705992978642175662L;

                                                      {
                                                          addAll(createExtensions());

                                                      }
                                                  };

        private static List<Object> createExtensions() {
            List<Object> result = new ArrayList<Object>();
            for (String cls : extensionClass) {
                try {
                    Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(cls);
                    if (ResourceUtils.isValidProvider(clazz) || Feature.class.isAssignableFrom(clazz)) {
                        result.add(clazz.newInstance());
                    }
                } catch (Exception e) {
                }
            }
            return result;
        }

    }

}
