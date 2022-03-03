package com.noseryoung.uek223.domain.exceptions;

public class NoAccessException extends Exception {
    public NoAccessException() {
        super("You don't have access to this resource");
    }
}
