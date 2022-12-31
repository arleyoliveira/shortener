package com.aom.shortener.application.usecase;

import com.aom.shortener.domain.services.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindShortener {

    private final ShortenerService service;

    @Autowired
    public FindShortener(ShortenerService service) {
        this.service = service;
    }

    public String findBySourceUrl(String sourceUrl) {
        return service.findBySourceUrl(sourceUrl).getShortenedUrl();
    }
}
