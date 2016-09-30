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
package com.tops001.dubbott.demo.consumer;

import javax.annotation.Resource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;

import com.tops001.dubbott.demo.user.facade.UserRestService;

/**
 * @author nealhu
 */
public class UserRestConsumerServiceImpl implements UserRestConsumerService {

    @Resource
    UserRestService restService;
    
    Builder client = ClientBuilder.newClient().target("http://192.168.255.100:8080/dubbott-demo-provider/v1.0/users/echo/neal").request();
    
    @Override
    public String echoService(String who) {

        return "[From consumer]: " + restService.echoService(who);
    }

    @Override
    public String apiEchoService(String who) {

        return "[From client api]: " + client.get(String.class);
    }
}
