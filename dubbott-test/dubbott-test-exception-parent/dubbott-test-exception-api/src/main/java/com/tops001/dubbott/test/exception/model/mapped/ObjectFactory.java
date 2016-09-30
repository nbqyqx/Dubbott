package com.tops001.dubbott.test.exception.model.mapped;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public CommentError createCommentError() {
        return new CommentError();
    }

    public Comment createComment() {
        return new Comment();
    }

    public CommentList createCommentList() {
        return new CommentList();
    }

}
