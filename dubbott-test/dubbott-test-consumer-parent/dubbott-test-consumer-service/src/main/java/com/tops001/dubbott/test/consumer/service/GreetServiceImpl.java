package com.tops001.dubbott.test.consumer.service;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.consumer.api.GreetService;

@Service
public class GreetServiceImpl implements GreetService {

	@Override
	public String welcome(String username) {
		return "Hello " + username + "!";
	}

}
