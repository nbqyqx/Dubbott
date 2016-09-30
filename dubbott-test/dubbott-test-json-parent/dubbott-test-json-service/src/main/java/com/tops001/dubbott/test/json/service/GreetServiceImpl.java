package com.tops001.dubbott.test.json.service;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.json.api.GreetService;

@Service
public class GreetServiceImpl implements GreetService {

	@Override
	public String welcome(String username) {
		return "Hello " + username + "!";
	}

}
