package com.tops001.dubbott.test.exception.model.nullconditions;

public class GuestbookNullException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public GuestbookNullException() {
        super();
    }

    public GuestbookNullException(String message) {
        super(message);
    }

    public GuestbookNullException(Throwable cause) {
        super(cause);
    }

    public GuestbookNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
