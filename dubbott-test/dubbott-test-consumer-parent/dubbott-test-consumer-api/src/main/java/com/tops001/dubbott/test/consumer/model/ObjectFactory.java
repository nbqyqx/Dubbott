package com.tops001.dubbott.test.consumer.model;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public Echo createPerson() {
        return new Echo();
    }
}
