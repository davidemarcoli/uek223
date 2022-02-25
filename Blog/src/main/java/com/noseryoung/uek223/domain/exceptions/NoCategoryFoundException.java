package com.noseryoung.uek223.domain.exceptions;

public class NoCategoryFoundException extends RuntimeException {
    public NoCategoryFoundException() {
        super("No category found");
    }

    public NoCategoryFoundException(String message) {
        super(message);
    }

    public NoCategoryFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoCategoryFoundException(Throwable cause) {
        super(cause);
    }
}
