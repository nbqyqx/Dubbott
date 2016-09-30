package com.tops001.dubbott.test.validation.service.formparam;

import javax.ws.rs.POST;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.validation.api.formparam.FormParameterValidationService;

@Service
public class FormParameterValidationServiceImpl implements FormParameterValidationService {

    @POST
    public String getSomething(String p1, String p2) {
        return p1 + ":" + p2;
    }
}
