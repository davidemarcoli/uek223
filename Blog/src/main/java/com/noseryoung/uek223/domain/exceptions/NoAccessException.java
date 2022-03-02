package com.noseryoung.uek223.domain.exceptions;

public class NoAccessException extends Exception{
    public NoAccessException() {
        super("You don't have access to this resource");
    }

    public NoAccessException(String message) {
        super(message);
    }

    public NoAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAccessException(Throwable cause) {
        super(cause);
    }
}
