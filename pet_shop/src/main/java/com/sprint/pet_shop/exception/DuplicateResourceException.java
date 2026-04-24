// This class defines an exception or error-handling helper for duplicate resource exception.
package com.sprint.pet_shop.exception;

public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}
