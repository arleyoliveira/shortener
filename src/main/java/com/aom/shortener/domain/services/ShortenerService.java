package com.aom.shortener.domain.services;

import com.aom.shortener.domain.entities.Shortener;
import com.aom.shortener.domain.entities.factories.ShortenerFactory;
import com.aom.shortener.domain.enums.ErrorMessages;
import com.aom.shortener.domain.exceptions.NotFound;
import com.aom.shortener.domain.exceptions.ValidateException;
import com.aom.shortener.domain.helpers.RandomString;
import com.aom.shortener.domain.repositories.ShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShortenerService {

    private static final int MAX_ATTEMPT = 10;
    private static final int SIZE_RANDOM = 6;
    private static final String SHORTENED_URL_FORMAT = "http://s.io/%s";

    private final ShortenerRepository repository;

    @Autowired
    public ShortenerService(ShortenerRepository repository) {
        this.repository = repository;
    }

    public void create(String sourceUrl) {
        Optional<Shortener> shortenerOptional = repository.findBySourceUrl(sourceUrl);

        if (shortenerOptional.isPresent()) {
            throw new ValidateException(ErrorMessages.EXISTING_SHORTENER.getMessage());
        }

        Shortener shortener = ShortenerFactory.create(sourceUrl, generateUniqueUrl());

        repository.save(shortener);
    }

    public Shortener findBySourceUrl(String sourceUrl) {
        return repository
                .findBySourceUrl(sourceUrl)
                .orElseThrow(() -> new NotFound(ErrorMessages.SHORTENER_NOT_FOUND.getMessage()));
    }

    private String generateUniqueUrl() {
        int attempt = 1;
        do {
            String randomUrl = generateShortenedUrl();

            Optional<Shortener> shortenerOptional = repository.findByShortenedUrl(randomUrl);

            if (shortenerOptional.isEmpty()) {
                return randomUrl;
            }
            attempt++;

        } while (attempt <= MAX_ATTEMPT);

        throw new ValidateException(ErrorMessages.UNABLE_TO_CREATE_SHORTENED_URL.getMessage());
    }

    private String generateShortenedUrl() {
        String random = RandomString.random(SIZE_RANDOM);
        return String.format(SHORTENED_URL_FORMAT, random);
    }
}
