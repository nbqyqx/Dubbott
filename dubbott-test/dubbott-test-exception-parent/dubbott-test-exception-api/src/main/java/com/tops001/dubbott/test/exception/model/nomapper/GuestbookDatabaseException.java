package com.tops001.dubbott.test.exception.model.nomapper;

public class GuestbookDatabaseException extends Exception {

    private static final long serialVersionUID = 3656497291087821230L;

    public GuestbookDatabaseException() {
        super();
    }

    public GuestbookDatabaseException(String message) {
        super(message);
    }

    public GuestbookDatabaseException(Throwable cause) {
        super(cause);
    }

    public GuestbookDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
