package com.aom.shortener.domain.entities;

import com.aom.shortener.domain.enums.ErrorMessages;
import com.aom.shortener.domain.exceptions.ValidateException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "shortened_urls")
@NoArgsConstructor
public class Shortener {
    private static final int SHORTENED_URL_MIN_SIZE = 5;
    private static final int SHORTENED_URL_MAX_SIZE = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "source_url", unique = true, nullable = false, length = 255)
    private String sourceUrl;

    @Column(name = "shortened_url", unique = true, nullable = false, length = SHORTENED_URL_MAX_SIZE)
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
