package com.aom.shortener.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {
    SHORTENED_URL_SIZE("Shortened field must have size between 5 and 10");

    private final String message;
}
