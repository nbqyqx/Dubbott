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
package com.tops001.dubbott.demo.common;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author lishen
 * @author Neal Hu
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {
    private static final long serialVersionUID = 116265872753285802L;

    @NotNull
    @Min(1L)
    private Long id;

    @JsonProperty("username")
    @XmlElement(name = "username")
    @NotNull
    @Size(min = 6, max = 50)
    private String name;

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", name='" + name + '\'' +
                ')';
    }
}
