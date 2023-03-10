package com.aom.shortener.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {
    SHORTENED_URL_SIZE("Shortened field must have size between 5 and 20"),

    SOURCE_URL_IS_REQUIRED("Source url is required"),

    SHORTENED_URL_IS_REQUIRED("Shortened url is required"),

    EXISTING_SHORTENER("Shortener already exists"),

    SOURCE_URL_IS_NOT_VALID("Source url is not valid"),

    UNABLE_TO_CREATE_SHORTENED_URL("Unable To Create Shortened Url"),

    SHORTENER_NOT_FOUND("Shortener not found");

    private final String message;
}
