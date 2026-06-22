package com.example.ems.exception;

/**
 * Thrown when attempting to create/update an employee with an email
 * that already belongs to another employee. Converted into a 409 response.
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}
