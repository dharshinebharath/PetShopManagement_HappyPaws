package com.sprint.pet_shop.exception;

//  Custom exception thrown when we try to create something that already exists.
//  For example, registering a customer with an email address we already have on file.

public class DuplicateResourceException extends RuntimeException {
    // Constructor that accepts an error message
    public DuplicateResourceException(String message) {
        super(message);
    }
}
