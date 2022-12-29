package com.aom.shortener.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {
    SHORTENED_URL_SIZE("Shortened field must have size between 5 and 10"),

    SOURCE_URL_IS_REQUIRED("Source url is required"),

    SHORTENED_URL_IS_REQUIRED("Shortened url is required"),

    EXISTING_SHORTENER("Shortener already exists"),

    SHORTENER_NOT_FOUND("Shortener not found");

    private final String message;
}
