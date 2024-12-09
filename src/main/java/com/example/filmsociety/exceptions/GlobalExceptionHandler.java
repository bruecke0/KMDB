package com.example.filmsociety.exceptions;


import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                 errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex) {

        
        if (ex.getCause() instanceof DateTimeParseException dateTimeParseException) {
            return new ResponseEntity<>(dateTimeParseException.getMessage(), HttpStatus.BAD_REQUEST);
        }
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Invalid input format: " + ex.getValue());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }
    
    @ExceptionHandler(IllegalArgumentException.class)
     public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
         return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
     }
}
