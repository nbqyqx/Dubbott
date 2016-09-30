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

/**
 * @author root on Jul 6, 2016 6:20:09 PM
 */
public class CXFRestClientExtensions {

    private List<Object> extensions;

    public List<Object> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<Object> extensions) {
        this.extensions = extensions;
    }
}
