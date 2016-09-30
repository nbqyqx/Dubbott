/*******************************************************************************
 * Copyright (c) 2016 Tops Tech Corp.
 *
 *
 * This software is the confidential and proprietary information of
 * Tops Tech Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with tops001.com. 
 * *******************************************************************************/

package com.tops001.dubbott.demo.beanvalidation;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BookWithValidation")
public class BookWithValidation {
    @NotNull(message = "why donot you give a name?")
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
}
