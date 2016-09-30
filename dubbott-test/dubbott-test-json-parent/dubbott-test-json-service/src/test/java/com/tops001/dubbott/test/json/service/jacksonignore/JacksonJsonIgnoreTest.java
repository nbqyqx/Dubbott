package com.tops001.dubbott.test.json.service.jacksonignore;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tops001.dubbott.test.json.api.jacksonignore.TestJacksonIgnoreService;
import com.tops001.dubbott.test.json.model.jacksonignore.TestPojo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-json-client.xml")
public class JacksonJsonIgnoreTest {

	@Autowired
	private TestJacksonIgnoreService testJacksonIgnoreService;

    @Test
    public void testJsonIgnore() throws IOException {
        TestPojo testPojo = testJacksonIgnoreService.get();
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(testPojo);
        assertEquals("{\"fish\":\"fish::{\\\"fish\\\":\\\"fish\\\"}::\\n\"}", result);
    }

}