package com.challenge.capitole.capitolechallenge.exception;

public class NotFoundException extends Exception {
    private static final long serialVersionUID = 1L;


    public NotFoundException(final String message) {
        super(message);
    }
}