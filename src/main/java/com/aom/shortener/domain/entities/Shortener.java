package com.aom.shortener.domain.entities;

import com.aom.shortener.domain.enums.ErrorMessages;
import com.aom.shortener.domain.exceptions.ValidateException;
import com.aom.shortener.domain.helpers.UrlValidator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "shorteners")
@NoArgsConstructor
public class Shortener {
    private static final int SHORTENED_URL_MIN_SIZE = 5;
    private static final int SHORTENED_URL_MAX_SIZE = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sourceUrl;

    private String shortenedUrl;

    public Shortener(String sourceUrl, String shortenedUrl) {
        this.sourceUrl = sourceUrl;
        this.shortenedUrl = shortenedUrl;
        validate();
    }

    private void validate() {
        if (sourceUrl.isEmpty()) {
            throw new ValidateException(ErrorMessages.SOURCE_URL_IS_REQUIRED.getMessage());
        }

        if (!UrlValidator.isValid(sourceUrl)) {
            throw new ValidateException(ErrorMessages.SOURCE_URL_IS_NOT_VALID.getMessage());
        }

        if (shortenedUrl.isEmpty()) {
            throw new ValidateException(ErrorMessages.SHORTENED_URL_IS_REQUIRED.getMessage());
        }

        if (!(shortenedUrl.length() >= SHORTENED_URL_MIN_SIZE
                && shortenedUrl.length() <= SHORTENED_URL_MAX_SIZE)
        ) {
            throw new ValidateException(ErrorMessages.SHORTENED_URL_SIZE.getMessage());
        }
    }
}
