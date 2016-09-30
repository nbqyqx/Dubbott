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
package com.tops001.dubbott.demo.user.facade;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.dubbo.rpc.RpcContext;
import com.tops001.dubbott.demo.common.ApiInvokeResult;
import com.tops001.dubbott.demo.common.User;

/**
 * @author nealhu
 */
public class UserRestServiceImpl implements UserRestService {

    // private static final Logger logger = LoggerFactory.getLogger(UserRestServiceImpl.class);

    private Map<Long, User> users = new HashMap<Long, User>();

    public User getUser(Long id/* , @Context HttpServletRequest request */) {
        // test context injection
        // System.out.println("Client address from @Context injection: " + (request != null ? request.getRemoteAddr() :
        // ""));
        // System.out.println("Client address from RpcContext: " + RpcContext.getContext().getRemoteAddressString());
        if (RpcContext.getContext().getRequest(HttpServletRequest.class) != null) {
            System.out.println("Client IP address from RpcContext: "
                               + RpcContext.getContext().getRequest(HttpServletRequest.class).getRemoteAddr());
        }
        if (RpcContext.getContext().getResponse(HttpServletResponse.class) != null) {
            System.out.println("Response object from RpcContext: "
                               + RpcContext.getContext().getResponse(HttpServletResponse.class));
        }
        return users.get(id);
    }

    public ApiInvokeResult<User> registerUser(User user) {
        users.put(user.getId(), user);
        return ApiInvokeResult.buildSuccesResult(user);
    }

    @Override
    public String echoService(String who) {
        
        return "hello " + who;
    }

    @Override
    public String beanParam(TalkListParam param, HttpServletRequest req, String ak) {
        String pageIndex = req.getParameter("pageIndex") + "|" +  req.getParameter("pageSize")+ "|||" + param.getCurrentPageIOS().toString() + " | page size:" + param.getPageSizeIOS().toString();
        
        return pageIndex + " ak:" + ak;
    }
}
