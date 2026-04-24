// This class defines an exception or error-handling helper for invalid data exception.
package com.sprint.pet_shop.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}
