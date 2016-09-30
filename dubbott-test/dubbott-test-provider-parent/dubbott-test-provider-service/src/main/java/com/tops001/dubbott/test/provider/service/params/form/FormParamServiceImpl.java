package com.tops001.dubbott.test.provider.service.params.form;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.params.form.FormParamService;

@Service
public class FormParamServiceImpl implements FormParamService {

	public FormParamServiceImpl() {

	}

	public String getRes(MultivaluedMap<String, String> entity) {
		return entity.toString();
	}

	public String getRes(String firstKey, MultivaluedMap<String, String> entity) {
		return "firstkey=" + firstKey + "&entity=" + entity.toString();
	}

	public String getStrEntity(String entity) {
		return "str:" + entity;
	}
}
