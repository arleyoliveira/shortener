package com.aom.shortener.application.rest.controllers;

import com.aom.shortener.application.dto.CreateShortenerDTO;
import com.aom.shortener.application.usecase.CreateShortener;
import com.aom.shortener.application.usecase.FindShortener;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shorteners")
@ResponseBody
public class ShortenerController {

    private final CreateShortener useCaseForCreate;
    private final FindShortener useCaseForFind;

    @Autowired
    public ShortenerController(CreateShortener useCase, FindShortener useCaseForFind) {
        this.useCaseForCreate = useCase;
        this.useCaseForFind = useCaseForFind;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String findBySourceUrl(CreateShortenerDTO dto) {
        System.out.println(dto);
        return useCaseForFind.findBySourceUrl(dto.getSourceUrl());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CreateShortenerDTO dto) {
        useCaseForCreate.create(dto);
    }
}
