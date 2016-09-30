package com.tops001.dubbott.test.exception.model.mapped;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "commenterror")
public class CommentError {

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    @XmlElement(name = "message")
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
