package com.tops001.dubbott.test.provider.service.params;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.params.CookieParamService;

@Service
public class CookieParamServiceImpl implements CookieParamService {

    public Response swipe(String jarSwipes) {
        return Response.ok("swiped:" + jarSwipes).cookie(new NewCookie("jar", (Integer
                        .valueOf(jarSwipes) + 1) + "")).build();
    }
    
}
