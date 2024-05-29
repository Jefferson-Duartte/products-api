package com.example.springboot.infra;

import com.example.springboot.exceptions.ExceptionResponse;
import com.example.springboot.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ProductNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ExceptionResponse> handleProductNotFound(ProductNotFoundException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

}
