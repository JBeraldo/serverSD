package com.sd.server.Exceptions;

public class UnauthorizedUserException extends Exception{
    public UnauthorizedUserException() {
        super("Usuário não autorizado");
    }

    public UnauthorizedUserException(String message) {
        super(message);
    }

    public UnauthorizedUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedUserException(Throwable cause) {
        super(cause);
    }

    public UnauthorizedUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
