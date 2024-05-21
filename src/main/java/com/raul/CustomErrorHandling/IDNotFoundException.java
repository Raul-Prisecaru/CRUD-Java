package com.raul.CustomErrorHandling;

public class IDNotFoundException extends Exception {
    public IDNotFoundException(String message) {
        super(message);
    }
}
