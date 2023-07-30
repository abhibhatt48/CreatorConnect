package com.example.creatorconnectbackend.exceptions;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//defining the class as a global exception handler.
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        // Extract error messages related to validation from the exception.
        String errorMessage = ex.getBindingResult()
                .getFieldErrors().stream()  // Stream the list of field errors.
                // For each error, extract the field's name and its associated error message.
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                // Join all error messages with commas.
                .collect(Collectors.joining(", "));

        // Return the collected error messages as the response body with a BAD_REQUEST (400) HTTP status.
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
