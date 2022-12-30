package com.aom.shortener.domain.services;

import com.aom.shortener.domain.entities.Shortener;
import com.aom.shortener.domain.entities.factories.ShortenerFactory;
import com.aom.shortener.domain.exceptions.NotFound;
import com.aom.shortener.domain.exceptions.ValidateException;
import com.aom.shortener.domain.helpers.RandomString;
import com.aom.shortener.domain.repositories.ShortenerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ShortenerServiceTest {

    @InjectMocks
    private ShortenerService service;

    @Mock
    private ShortenerRepository repository;

    @Test
    public void shouldSuccessfullyCreateShortenedUrl() {
        var sourceUrl = "www.test.com";
        var optionalEmpty = Optional.<Shortener>empty();
        var randomText = "1ab2";
        var shortenedUrl = String.format("https://link.to/%s", randomText);
        var shortener = new Shortener(sourceUrl, shortenedUrl);

        when(repository.findBySourceUrl(sourceUrl)).thenReturn(optionalEmpty);
        when(repository.findByShortenedUrl(shortenedUrl)).thenReturn(optionalEmpty);
        when(repository.save(shortener)).thenReturn(shortener);


        try (MockedStatic<RandomString> RandomStringMock = Mockito.mockStatic(RandomString.class)) {
            RandomStringMock.when(() -> RandomString.random(randomText.length()))
                    .thenReturn(randomText);
        }

        try (MockedStatic<ShortenerFactory> ShortenerFactoryMock = Mockito.mockStatic(ShortenerFactory.class)) {
            ShortenerFactoryMock.when(() -> ShortenerFactory.create(sourceUrl, shortenedUrl))
                    .thenReturn(shortener);
        }

        service.create(sourceUrl);
    }

    @Test
    public void shouldThrowExceptionWhenTryingToCreateEqualShortener() {
        var sourceUrl = "www.test.com";
        var randomText = "1ab2";
        var shortenedUrl = String.format("https://link.to/%s", randomText);
        var shortener = new Shortener(sourceUrl, shortenedUrl);
        var optionalEmpty = Optional.<Shortener>empty();
        var optionalContains = Optional.of(shortener);

        when(repository.findBySourceUrl(anyString())).thenReturn(optionalEmpty);
        when(repository.findByShortenedUrl(anyString())).thenReturn(optionalContains);
        when(repository.save(shortener)).thenReturn(shortener);


        try (MockedStatic<RandomString> RandomStringMock = Mockito.mockStatic(RandomString.class)) {
            RandomStringMock.when(() -> RandomString.random(anyInt()))
                    .thenReturn(randomText);
        }

        try (MockedStatic<ShortenerFactory> ShortenerFactoryMock = Mockito.mockStatic(ShortenerFactory.class)) {
            ShortenerFactoryMock.when(() -> ShortenerFactory.create(sourceUrl, shortenedUrl))
                    .thenReturn(shortener);
        }

        assertThrows(ValidateException.class, () -> {
            service.create(sourceUrl);
        });
    }

    @Test
    public void shouldThrowExceptionWhenTryingToCreateShortenerWithExistingSourceUrl() {
        var sourceUrl = "www.test.com";
        var randomText = "1ab2";
        var shortenedUrl = String.format("https://link.to/%s", randomText);
        var shortener = new Shortener(sourceUrl, shortenedUrl);
        var optionalContains = Optional.of(shortener);

        when(repository.findBySourceUrl(anyString())).thenReturn(optionalContains);

        assertThrows(ValidateException.class, () -> {
            service.create(sourceUrl);
        });
    }

    @Test
    public void shouldSuccessfullyFindShortenedUrl() {
        var sourceUrl = "www.test.com";
        var shortenedUrl = "www.link.to/102030";
        var shortener = new Shortener(sourceUrl, shortenedUrl);
        var optionalContains = Optional.of(shortener);

        when(repository.findBySourceUrl(sourceUrl)).thenReturn(optionalContains);

        var shortenerResult = service.findBySourceUrl(sourceUrl);

        Assertions.assertThat(shortenerResult.getShortenedUrl()).isEqualTo(shortenedUrl);
        Assertions.assertThat(shortenerResult.getSourceUrl()).isEqualTo(sourceUrl);
        Assertions.assertThatObject(shortenerResult).isInstanceOf(Shortener.class);
    }

    @Test
    public void shouldThrowExceptionWhenNotFindingShortenedUrl() {
        var sourceUrl = "www.test.com";
        var optionalEmpty = Optional.<Shortener>empty();

        when(repository.findBySourceUrl(sourceUrl)).thenReturn(optionalEmpty);

        assertThrows(NotFound.class, () -> {
            service.findBySourceUrl(sourceUrl);
        });
    }
}