package com.aom.shortener.application.rest.controllers;

import com.aom.shortener.application.rest.ApiErrors;
import com.aom.shortener.domain.exceptions.Exception;
import com.aom.shortener.domain.exceptions.NotFound;
import com.aom.shortener.domain.exceptions.ValidateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(ValidateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationException(ValidateException exception) {
        return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleNotFoundException(NotFound exception) {
        return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrors handleException(Exception exception) {
        return new ApiErrors(exception.getMessage());
    }
}
