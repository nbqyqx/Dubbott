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

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.StringUtils;

/**
 * @author lizhe on 2016.6.15 6:36:17 PM
 */
public abstract class BaseRestServer implements RestServer {

    public void start(URL url) {
        // getDeployment().getMediaTypeMappings().put("json", "application/json");
        // getDeployment().getMediaTypeMappings().put("xml", "text/xml");
        // server.getDeployment().getMediaTypeMappings().put("xml", "application/xml");
        
//        getDeployment().addProvider(RpcContextFilter.class.getName());
        // users can override this mapper, but we just rely on the current priority strategy of resteasy
//        getDeployment().addProvider(RpcExceptionMapper.class.getName());
              
        doStart(url);
        loadProviders(url.getParameter(Constants.EXTENSION_KEY, ""));
        //getDeployment().addProvider(JacksonJaxbJsonProvider.class.getName());
        
    }

    public void deploy(@SuppressWarnings("rawtypes") Class resourceDef, Object resourceInstance, String contextPath) {
        getDeployment().addResourceClass(resourceDef, resourceInstance);

    }

    public void undeploy(@SuppressWarnings("rawtypes") Class resourceDef) {
        getDeployment().removeResourceClass(resourceDef);
    }

    protected void loadProviders(String value) {
        for (String clazz : Constants.COMMA_SPLIT_PATTERN.split(value)) {
            if (!StringUtils.isEmpty(clazz)) {
                getDeployment().addProvider(clazz.trim());
            }
        }
    }

    protected abstract CXFDeployment getDeployment();

    protected abstract void doStart(URL url);
}
