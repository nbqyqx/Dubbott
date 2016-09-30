package com.tops001.dubbott.test.json.service.jacksonignore;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tops001.dubbott.test.json.api.jacksonignore.TestJacksonIgnoreService;
import com.tops001.dubbott.test.json.model.jacksonignore.TestPojo;

@Service
public class TestJacksonIgnoreServiceImpl implements TestJacksonIgnoreService {

	public TestPojo get() throws JsonProcessingException {
		TestPojo n = new TestPojo();
		ObjectMapper om = new ObjectMapper();
		String v = om.writeValueAsString(n);
		TestPojo t = new TestPojo();
		t.setFish("fish::" + v + "::\n");
		return t;
	}

}