package com.noseryoung.uek223.domain.exceptions;

public class InvalidObjectException extends Exception {
    public InvalidObjectException() {
        super("Unable to handle the provided object");
    }

    public InvalidObjectException(String message) {
        super(message);
    }
}
