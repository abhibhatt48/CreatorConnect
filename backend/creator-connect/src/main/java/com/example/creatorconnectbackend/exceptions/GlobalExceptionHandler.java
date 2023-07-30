/**
 * -----------------------------------------------------------------------------
 *                 Global Exception Handler
 * -----------------------------------------------------------------------------
 * Purpose:
 * The 'GlobalExceptionHandler' class is a centralized exception handler 
 * designed to manage and respond to specific exceptions encountered within the 
 * 'com.example.creatorconnectbackend' application. By using a global handler, 
 * consistent error responses can be maintained, providing a more coherent user 
 * experience and simplifying debugging efforts.
 *
 * Key Feature:
 * - Validation Error Handling: Captures validation exceptions, especially from 
 *   incoming HTTP requests, and constructs a meaningful error message to relay back 
 *   to the client.
 *
 * Annotations:
 * - @ControllerAdvice: This annotation designates the class as a global exception 
 *   handler, making it capable of intercepting exceptions thrown by methods annotated 
 *   with @RequestMapping or one of its shortcuts.
 *
 * Core Exception Handling:
 * - MethodArgumentNotValidException: This is typically thrown when validation on 
 *   an argument annotated with @Valid fails. The handler constructs a descriptive 
 *   error message that pinpoints which fields failed validation and why.
 * -----------------------------------------------------------------------------
 */

package com.example.creatorconnectbackend.exceptions;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors().stream()  
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
