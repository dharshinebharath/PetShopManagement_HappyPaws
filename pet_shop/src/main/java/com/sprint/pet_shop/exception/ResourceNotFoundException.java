package com.sprint.pet_shop.exception;

// Custom exception thrown when we search the database for a specific item (like a Pet ID) 
// and come up empty-handed.
public class ResourceNotFoundException extends RuntimeException {
    // Constructor that accepts an error message
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
