package com.aom.shortener.domain.exceptions;

abstract public class Exception extends RuntimeException {
    public Exception(String message) {
        super(message);
    }
}
