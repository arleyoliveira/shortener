package com.aom.shortener.domain.repositories;

import com.aom.shortener.domain.entities.Shortener;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortenerRepository {

    public Optional<Shortener> findBySourceUrl(String sourceUrl);

    public Shortener save(Shortener shortener);
}
