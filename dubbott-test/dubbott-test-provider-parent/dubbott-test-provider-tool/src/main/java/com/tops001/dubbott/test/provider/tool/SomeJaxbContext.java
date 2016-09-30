package com.tops001.dubbott.test.provider.tool;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Validator;

public class SomeJaxbContext extends JAXBContext
{
    @Override
    public Marshaller createMarshaller()
                    throws JAXBException
    {
        return new SomeMarshaller();
    }

    @Override
    public Unmarshaller createUnmarshaller() throws JAXBException
    {
        return new SomeUnmarshaller();
    }

    @Override
    public Validator createValidator() throws JAXBException
    {
        return null;
    }
}