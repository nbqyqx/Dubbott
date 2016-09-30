package com.tops001.dubbott.test.consumer.service;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.consumer.api.TimeoutService;

@Service
public class TimeoutServiceImpl implements TimeoutService {

    public String timeout(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (Exception e) {
        	// nothing to do
        }
        return "request processed";
    }
}
