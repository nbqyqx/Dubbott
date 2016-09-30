package com.tops001.dubbott.demo.user.facade;

import javax.ws.rs.client.InvocationCallback;


public class EchoServiceCallback implements InvocationCallback<String> {

    public static String res = null;
    @Override
    public void completed(String response) {
        res = response;
        System.out.println("EchoServiceCallback completed!");
        
    }

    @Override
    public void failed(Throwable throwable) {
        System.out.println("EchoServiceCallback failed!");
        
        
    }

}
