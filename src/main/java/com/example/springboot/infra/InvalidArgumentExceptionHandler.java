package com.example.springboot.infra;

import com.example.springboot.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.Objects;

@ControllerAdvice
public class InvalidArgumentExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),  "'" + Objects.requireNonNull(exception.getBindingResult().getFieldError()).getField() + "'" + " field is not valid" );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Invalid request content. Please check the data format.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

}
