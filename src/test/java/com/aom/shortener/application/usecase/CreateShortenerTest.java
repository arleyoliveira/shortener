package com.aom.shortener.application.usecase;

import com.aom.shortener.application.dto.ShortenerRequestDTO;
import com.aom.shortener.domain.exceptions.NotFound;
import com.aom.shortener.domain.services.ShortenerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@ExtendWith(SpringExtension.class)
class CreateShortenerTest {

    @InjectMocks
    private CreateShortener createShortener;

    @Mock
    private ShortenerService service;

    @Test
    public void shouldCreateShortenerWithSuccessful() {
        var dto = new ShortenerRequestDTO();
        dto.setSourceUrl("www.test.com");

        doNothing().when(service).create(dto.getSourceUrl());

        createShortener.create(dto);
    }


    @Test()
    public void shouldCreateShortenerWithError() {
        var dto = new ShortenerRequestDTO();
        dto.setSourceUrl("www.test.com");

        doThrow(NotFound.class).when(service).create(dto.getSourceUrl());

        assertThrows(NotFound.class, () -> {
            createShortener.create(dto);
        });
    }
}