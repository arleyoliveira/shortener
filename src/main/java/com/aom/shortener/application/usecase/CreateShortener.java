package com.aom.shortener.application.usecase;

import com.aom.shortener.application.dto.CreateShortenerDTO;
import com.aom.shortener.domain.services.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateShortener {

    private final ShortenerService service;

    @Autowired
    public CreateShortener(ShortenerService service) {
        this.service = service;
    }

    public void create(CreateShortenerDTO dto) {
        service.create(dto.getSourceUrl());
    }
}
