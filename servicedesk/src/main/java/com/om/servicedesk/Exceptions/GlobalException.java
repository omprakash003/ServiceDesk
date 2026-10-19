package com.om.servicedesk.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException re){
        return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<String>duplicateHandler(DuplicateResourceException de){
        return new ResponseEntity<>(de.getMessage(),HttpStatus.CONFLICT);
    }
}
