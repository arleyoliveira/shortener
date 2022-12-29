package com.aom.shortener.domain.entities.factories;

import com.aom.shortener.domain.entities.Shortener;

public class ShortenerFactory {

    public static Shortener create(String sourceUrl, String shortenedUrl) {
        return new Shortener(sourceUrl, shortenedUrl);
    }
}
