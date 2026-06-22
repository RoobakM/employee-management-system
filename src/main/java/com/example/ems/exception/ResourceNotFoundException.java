package com.example.ems.exception;

/**
 * Thrown whenever a lookup (by id, etc.) fails to find a matching employee.
 * Caught centrally by GlobalExceptionHandler and converted into a 404 response.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
