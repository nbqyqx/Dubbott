package com.tops001.dubbott.test.json.model.json;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Country {
    private String code;
    private String name;

    public Country() {}

    public Country(String code, String name) {
        super();
        this.code = code;
        this.name = name;
    }

    public java.lang.String getCode() {
        return code;
    }

    public void setCode(java.lang.String code) {
        this.code = code;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

}
