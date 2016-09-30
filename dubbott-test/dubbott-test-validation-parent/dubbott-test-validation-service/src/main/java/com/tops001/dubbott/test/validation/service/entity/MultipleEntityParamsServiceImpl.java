package com.tops001.dubbott.test.validation.service.entity;

import javax.ws.rs.GET;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.validation.api.entity.MultipleEntityParamsService;

@Service
public class MultipleEntityParamsServiceImpl implements MultipleEntityParamsService {

    @GET
    public void getMultipleEntity(String s1, String s2) {
        return;
    }
}
