package com.tops001.dubbott.test.exception.model.nullconditions;

public class GuestbookException extends Exception {

    private static final long serialVersionUID = -6902290168001222679L;

    public GuestbookException() {
        super();
    }

    public GuestbookException(String message) {
        super(message);
    }

    public GuestbookException(Throwable cause) {
        super(cause);
    }

    public GuestbookException(String message, Throwable cause) {
        super(message, cause);
    }
}
