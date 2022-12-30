package com.aom.shortener.infrastructure.db.jpa;

import com.aom.shortener.domain.entities.Shortener;
import com.aom.shortener.infrastructure.db.jpa.impl.ShortenerJpaRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ShortenerJpaRepositoryTest {

    @InjectMocks
    private ShortenerJpaRepository repository;

    @Mock
    private ShortenerJpaRepositoryImpl impl;

    @Test
    public void shouldFindAShortenerByTheSourceUrl() {
        var sourceUrl = "www.test.com";
        var shortenedUrl = "https://link.to/ab20";
        var shortener = new Shortener(sourceUrl, shortenedUrl);

        var shortenerList = List.<Shortener>of(shortener);

        when(impl.findBySourceUrl(sourceUrl)).thenReturn(shortenerList);

        var shortenerOptionalResult = repository.findBySourceUrl(sourceUrl);

        Assertions.assertThat(shortenerOptionalResult.get()).isInstanceOf(Shortener.class);
        Assertions.assertThat(shortenerOptionalResult.get().getSourceUrl()).isEqualTo(sourceUrl);
        Assertions.assertThat(shortenerOptionalResult.get().getShortenedUrl()).isEqualTo(shortenedUrl);
    }

    @Test
    public void shouldFindAShortenerByTheShortenedUrl() {
        var sourceUrl = "www.test.com";
        var shortenedUrl = "https://link.to/ab20";
        var shortener = new Shortener(sourceUrl, shortenedUrl);

        var shortenerList = List.<Shortener>of(shortener);

        when(impl.findByShortenedUrl(shortenedUrl)).thenReturn(shortenerList);

        var shortenerOptionalResult = repository.findByShortenedUrl(shortenedUrl);

        Assertions.assertThat(shortenerOptionalResult.get()).isInstanceOf(Shortener.class);
        Assertions.assertThat(shortenerOptionalResult.get().getSourceUrl()).isEqualTo(sourceUrl);
        Assertions.assertThat(shortenerOptionalResult.get().getShortenedUrl()).isEqualTo(shortenedUrl);
    }

    @Test
    public void shouldSuccessfullySaveAShortenedUrl() {
        var sourceUrl = "www.test.com";
        var shortenedUrl = "https://link.to/ab20";
        var shortener = new Shortener(sourceUrl, shortenedUrl);

        when(impl.save(shortener)).thenReturn(shortener);

        var shortenerResult = repository.save(shortener);

        Assertions.assertThat(shortenerResult).isInstanceOf(Shortener.class);
        Assertions.assertThat(shortenerResult.getSourceUrl()).isEqualTo(sourceUrl);
        Assertions.assertThat(shortenerResult.getShortenedUrl()).isEqualTo(shortenedUrl);
    }
}