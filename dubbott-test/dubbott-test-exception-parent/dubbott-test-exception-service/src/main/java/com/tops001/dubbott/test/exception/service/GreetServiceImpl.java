package com.tops001.dubbott.test.exception.service;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.exception.api.GreetService;

@Service
public class GreetServiceImpl implements GreetService {

	@Override
	public String welcome(String username) {
		return "Hello " + username + "!";
	}

}
