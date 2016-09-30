package com.tops001.dubbott.test.provider.tool;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

@Provider
public class JaxbContextProvider
                implements ContextResolver<JAXBContext>
{
    @Override
    public JAXBContext getContext(Class<?> type)
    {
        System.out.println("JAXBContextProvider.getContext is working now");
        JAXBContext ctx = new SomeJaxbContext();
        return ctx;
    }
}