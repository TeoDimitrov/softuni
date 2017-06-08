package com.wizzmail.exception;

import javax.ejb.EJBException;

public class InvalidRecipientException extends RuntimeException {

    private static final long serialVersionUID = 101654747L;

    public InvalidRecipientException() {
    }

    public InvalidRecipientException(String message) {
        super(message);
    }

    public InvalidRecipientException(Exception ex) {
        super(ex);
    }

    public InvalidRecipientException(String message, Exception ex) {
        super(message, ex);
    }

    public Exception getCausedByException() {
        return (Exception)this.getCause();
    }
}
