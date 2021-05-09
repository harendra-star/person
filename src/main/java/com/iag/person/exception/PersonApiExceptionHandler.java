package com.iag.person.exception;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Priority;

@ControllerAdvice
@Priority(100)
public class PersonApiExceptionHandler {
    @ExceptionHandler(PersonApiException.class)
    public ResponseEntity<Object> handlePersonApiException(
            PersonApiException ex, WebRequest request) {
        return ResponseEntity.status(ex.getStatus())
                .contentType(MediaType.APPLICATION_JSON).body(ex);
    }
}
