package com.noseryoung.uek223.domain.exceptions;

public class NoBlogPostFoundException extends RuntimeException {
    public NoBlogPostFoundException() {
        super("No blog post found");
    }

    public NoBlogPostFoundException(String message) {
        super(message);
    }
}
