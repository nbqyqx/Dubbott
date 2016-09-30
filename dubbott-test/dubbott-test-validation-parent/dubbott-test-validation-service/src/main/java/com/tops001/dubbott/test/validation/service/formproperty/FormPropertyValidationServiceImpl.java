package com.tops001.dubbott.test.validation.service.formproperty;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.validation.api.formproperty.FormPropertyValidationService;

@Service
public class FormPropertyValidationServiceImpl implements FormPropertyValidationService {

	@Override
	public String doSomething(String p1, String something) {
		return p1 + ":" + something;
	}
	
}
