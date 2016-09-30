package com.tops001.dubbott.test.consumer.service;

import static org.junit.Assert.*;

import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.rpc.RpcContext;
import com.tops001.dubbott.test.consumer.api.EchoService;
import com.tops001.dubbott.test.consumer.api.TimeoutService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-consumer-client.xml")
public class ClientTest {

	@Autowired
	private EchoService echoService;
	@Autowired
	@Qualifier("TimeoutService")
	private TimeoutService timeoutService;
	@Autowired
	@Qualifier("AsyncTimeoutService")
	private TimeoutService asyncTimeoutService;
//	@Autowired
//	@Qualifier("AsyncDboTimeoutService")
//	private TimeoutService dboAsyncTimeoutService;

    @Test
    public void testAcceptHeader() throws Exception {
    	Response response = echoService.getAcceptHeaderEcho(null, null);
        assertEquals("echo: "+MediaType.APPLICATION_XML, response.readEntity(String.class));
    }
    
    @Test(expected=SocketTimeoutException.class)
    public void testTimeoutService() throws Throwable {
    	try {
    		timeoutService.timeout(20000);
    	} catch(Exception e) {
    		throw e.getCause();
    	}
    }
    
    @Test
    @Ignore("目前，dubbott的客户端在使用cxfrest协议时不支持客户端异步调用")
    public void testAsyncTimeoutService() throws InterruptedException, ExecutionException {
    	String result = asyncTimeoutService.timeout(10000);
    	System.out.println(result);
    	assertNull(result);
    	
    	Future<String> future = RpcContext.getContext().getFuture();
    	long start = System.currentTimeMillis();
    	result = future.get();
    	long finished = System.currentTimeMillis();
    	System.out.println("asyncTimeoutService.timeout(10000) costs " + (finished - start) + "ms.");
    	assertEquals("request processed", result);
    }
    
//    @Test
//    public void testAsyncDboTimeoutService() throws InterruptedException, ExecutionException {
//    	String result = dboAsyncTimeoutService.timeout(10000);
//    	System.out.println(result);
//    	assertNull(result);
//    	
//    	Future<String> future = RpcContext.getContext().getFuture();
//    	long start = System.currentTimeMillis();
//    	result = future.get();
//    	long finished = System.currentTimeMillis();
//    	System.out.println("dboAsyncTimeoutService.timeout(10000) costs " + (finished - start) + "ms.");
//    	assertEquals("request processed", result);
//    }

}
