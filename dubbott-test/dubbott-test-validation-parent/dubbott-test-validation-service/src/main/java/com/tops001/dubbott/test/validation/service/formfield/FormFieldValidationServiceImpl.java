package com.tops001.dubbott.test.validation.service.formfield;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.validation.api.formfield.FormFieldValidationService;

@Service
public class FormFieldValidationServiceImpl implements FormFieldValidationService {

	@Override
	public String getSomething(String something) {
		return p1 + ":" + something;
	}
	
	private String p1;

}
