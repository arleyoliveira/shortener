package com.aom.shortener.infrastructure.db.jpa.impl;

import com.aom.shortener.domain.entities.Shortener;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShortenerJpaRepositoryImpl extends JpaRepository<Shortener, Integer> {

    public List<Shortener> findBySourceUrl(String sourceUrl);


}
