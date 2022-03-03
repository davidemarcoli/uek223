package com.noseryoung.uek223.domain.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<String> handleAccessDeniedAxception(AccessDeniedException ex, WebRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>("Your user \"" + username + "\" doesn't have access to this ressource!", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<String> handleUnauthorized(AuthenticationException ex, WebRequest request) {
        return new ResponseEntity<>("Please enter the correct login details!", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
}
