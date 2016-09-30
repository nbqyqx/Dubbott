package com.tops001.dubbott.test.exception.model.nullconditions;

public class GuestbookThrowException extends Exception {

    private static final long serialVersionUID = 1L;

    public GuestbookThrowException() {
        super();
    }

    public GuestbookThrowException(String message) {
        super(message);
    }

    public GuestbookThrowException(Throwable cause) {
        super(cause);
    }

    public GuestbookThrowException(String message, Throwable cause) {
        super(message, cause);
    }
}
