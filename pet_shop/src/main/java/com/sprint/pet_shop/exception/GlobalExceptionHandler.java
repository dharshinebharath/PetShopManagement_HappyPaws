package com.sprint.pet_shop.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

//  A central hub for handling all exceptions thrown across the application.
//  Instead of dealing with try-catch blocks everywhere, this class intercepts 
//  errors and formats them into clean, friendly HTTP responses for the client.

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Catches ResourceNotFoundException and returns a 404 status.
    // Happens when someone searches for an ID that doesn't exist in our database.

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Catches DuplicateResourceException and returns a 400 Bad Request status.
    // Triggers when there's a conflict, like a duplicate email.

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<String> handleDuplicate(DuplicateResourceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Catches InvalidDataException and returns a 400 Bad Request.
    // This catches manually thrown validation errors (like negative prices).

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> handleInvalid(InvalidDataException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Automatically intercepts Bean Validation errors (like @NotBlank failures).
    // Extracts the specific error message and sends it back to the user.

    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidation(HandlerMethodValidationException ex) {

        Map<String, Object> response = new HashMap<>();

        List<String> errors = ex.getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        response.put("status", 400);
        response.put("errors", errors);

        return response;
    }
   
    // The ultimate fallback handler for any unexpected crashes.
    // Prevents stack traces from leaking to the client and returns a generic 500 error.
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
