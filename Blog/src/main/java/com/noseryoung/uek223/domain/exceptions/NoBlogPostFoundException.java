package com.noseryoung.uek223.domain.exceptions;

public class NoBlogPostFoundException extends RuntimeException {
    public NoBlogPostFoundException() {
        super("No blog post found");
    }

    public NoBlogPostFoundException(String message) {
        super(message);
    }

    public NoBlogPostFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoBlogPostFoundException(Throwable cause) {
        super(cause);
    }
}
