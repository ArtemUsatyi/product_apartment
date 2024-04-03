package com.example.product_apartment.exсeption;

import org.hibernate.boot.beanvalidation.IntegrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RentApartmentExceptionHandler {


    @ExceptionHandler(BookingException.class)
    public ResponseEntity<?> exceptionHandler(BookingException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}

