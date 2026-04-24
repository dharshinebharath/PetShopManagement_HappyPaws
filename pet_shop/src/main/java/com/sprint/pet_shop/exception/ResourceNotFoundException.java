// This class defines an exception or error-handling helper for resource not found exception.
package com.sprint.pet_shop.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
