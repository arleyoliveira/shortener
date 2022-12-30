package com.aom.shortener.application.usecase;

import com.aom.shortener.application.dto.ShortenerRequestDTO;
import com.aom.shortener.domain.entities.Shortener;
import com.aom.shortener.domain.entities.factories.ShortenerFactory;
import com.aom.shortener.domain.exceptions.NotFound;
import com.aom.shortener.domain.services.ShortenerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FindShortenerTest {

    @InjectMocks
    private FindShortener findShortener;

    @Mock
    private ShortenerService service;


    @Test
    public void shouldGetShortenerWithSuccessful() {
        //Arrange
        var sourceUrl = "www.test.com";
        var shortenerExpected = "https://link.to/1234";

        var shortener = ShortenerFactory.create(sourceUrl, shortenerExpected);

        when(service.findBySourceUrl(sourceUrl)).thenReturn(shortener);

        var shortenerResult = findShortener.findBySourceUrl(sourceUrl);

        Assertions.assertThat(shortenerResult).isEqualTo(shortenerExpected);
    }

    @Test
    public void shouldGetShortenerWithError() {
        //Arrange
        var sourceUrl = "www.test.com";

        doThrow(NotFound.class).when(service).findBySourceUrl(sourceUrl);

        assertThrows(NotFound.class, () -> {
            findShortener.findBySourceUrl(sourceUrl);
        });
    }
}