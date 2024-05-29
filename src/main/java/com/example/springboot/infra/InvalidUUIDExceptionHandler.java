package com.example.springboot.infra;

import com.example.springboot.exceptions.ExceptionResponse;
import com.example.springboot.exceptions.InvalidUUIDException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice
public class InvalidUUIDExceptionHandler {

    @ExceptionHandler(InvalidUUIDException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ExceptionResponse> handleInvalidUUID(InvalidUUIDException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
}
