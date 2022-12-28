package com.microservice.propertyservice.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
