package com.aom.shortener.infrastructure.db.jpa;

import com.aom.shortener.domain.entities.Shortener;
import com.aom.shortener.domain.repositories.ShortenerRepository;
import com.aom.shortener.infrastructure.db.jpa.impl.ShortenerJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ShortenerJpaRepository implements ShortenerRepository {

    private final ShortenerJpaRepositoryImpl impl;

    @Autowired
    public ShortenerJpaRepository(ShortenerJpaRepositoryImpl impl) {
        this.impl = impl;
    }

    @Override
    public Optional<Shortener> findBySourceUrl(String sourceUrl) {
        return impl.findBySourceUrl(sourceUrl).stream().findFirst();
    }

    @Override
    public Optional<Shortener> findByShortenedUrl(String shortenedUrl) {
        return impl.findByShortenedUrl(shortenedUrl).stream().findFirst();
    }

    @Override
    public Shortener save(Shortener shortener) {
        return impl.save(shortener);
    }
}
