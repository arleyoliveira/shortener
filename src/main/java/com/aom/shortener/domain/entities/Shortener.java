package com.aom.shortener.domain.entities;

import com.aom.shortener.domain.exceptions.ValidateException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.Validate;

@Getter
@Entity
@Table(name = "shortened_urls")
@NoArgsConstructor
public class Shortener {

    private static final int SHORTENED_URL_MIN_SIZE = 5;
    private static final int SHORTENED_URL_MAX_SIZE = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "source_url", unique = true, nullable = false, length = 255)
    private String sourceUrl;

    @Column(name = "shortened_url", unique = true, nullable = false, length = 10)
    private String shortenedUrl;

    public Shortener(String sourceUrl, String shortenedUrl) {
        validate(sourceUrl, shortenedUrl);
        this.sourceUrl = sourceUrl;
        this.shortenedUrl = shortenedUrl;
    }

    private void validate(String sourceUrl, String shortenedUrl) {
        if (sourceUrl.isEmpty()) {
            throw new ValidateException("Source url is required!");
        }

        if (shortenedUrl.isEmpty()) {
            throw new ValidateException("Shortened url is required!");
        }

        if (!(shortenedUrl.length() >= SHORTENED_URL_MIN_SIZE
                && shortenedUrl.length() <= SHORTENED_URL_MAX_SIZE)
        ) {
            throw new ValidateException("Shortened url is required!");
        }

        Validate.inclusiveBetween(SHORTENED_URL_MIN_SIZE, SHORTENED_URL_MAX_SIZE, shortenedUrl.length(), "Shortened field must have size between 5 and 10");
    }

    @Override
    public String toString() {
        return "Shortener{" +
                "id=" + id +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", shortenedUrl='" + shortenedUrl + '\'' +
                '}';
    }
}
