package com.example.demo.controller;

import com.example.demo.exceptions.ErrorMessage;
import com.example.demo.exceptions.NoPersonException;
import com.example.demo.exceptions.NoRelationshipException;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = {NoPersonException.class, NoRelationshipException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleGenericNotFoundExceptionPerson(Exception ex, WebRequest request) {
        ErrorMessage error = new ErrorMessage(400, new Date() , "Some data that you introduced was not valid! We recommend you to check the data you send first and then the method you use first..", "Advice request.");
        return error;
    }
}

