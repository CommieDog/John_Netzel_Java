package com.company.musicstorecatalog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String noSuchElementFound(NoSuchElementException e) {
        return "No record found, please ensure that ID is correct.";
    }
}
