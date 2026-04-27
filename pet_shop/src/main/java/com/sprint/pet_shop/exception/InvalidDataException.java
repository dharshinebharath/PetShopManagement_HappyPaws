package com.sprint.pet_shop.exception;

// Custom exception used when the incoming data doesn't pass our business rules.
// For instance, trying to set an employee's hire date to the future.

public class InvalidDataException extends RuntimeException {
    // Constructor that accepts an error message
    public InvalidDataException(String message) {
        super(message);
    }
}
