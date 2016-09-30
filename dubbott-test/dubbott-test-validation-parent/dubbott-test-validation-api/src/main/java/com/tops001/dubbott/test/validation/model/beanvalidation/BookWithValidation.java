package com.tops001.dubbott.test.validation.model.beanvalidation;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BookWithValidation")
public class BookWithValidation {
    @NotNull
    private String name;
    private String id;

    public BookWithValidation() {}

    public BookWithValidation(String id) {
        this.id = id;
    }

    public BookWithValidation(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setId(String i) {
        id = i;
    }

    public String getId() {
        return id;
    }

	@Override
	public String toString() {
		return "BookWithValidation [name=" + name + ", id=" + id + "]";
	}
    
}
