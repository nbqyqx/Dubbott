package com.tops001.dubbott.test.consumer.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Echo {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
