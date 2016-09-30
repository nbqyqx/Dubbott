package com.tops001.dubbott.test.exception.model.mapped;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comments")
public class CommentList {

    private List<Comment> comments = new ArrayList<Comment>();

    public CommentList() {
        /* do nothing */
    }

    @XmlElement(name = "comment")
    public List<Comment> getComments() {
        return comments;
    }

    public void setMessages(List<Comment> comments) {
        this.comments = comments;
    }
}
