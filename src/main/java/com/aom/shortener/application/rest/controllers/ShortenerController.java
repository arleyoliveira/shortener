package com.aom.shortener.application.rest.controllers;

import com.aom.shortener.application.dto.ShortenerRequestDTO;
import com.aom.shortener.application.usecase.CreateShortener;
import com.aom.shortener.application.usecase.FindShortener;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shorteners")
@ResponseBody
@Tag(name = "shorteners")
public class ShortenerController {

    private final CreateShortener useCaseForCreate;
    private final FindShortener useCaseForFind;

    @Autowired
    public ShortenerController(CreateShortener useCase, FindShortener useCaseForFind) {
        this.useCaseForCreate = useCase;
        this.useCaseForFind = useCaseForFind;
    }

    @Operation(summary = "Find By Shource Url", tags = { "shorteners" })
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String findBySourceUrl(@Parameter(name = "Filter", description = "Find shortener object") @Valid ShortenerRequestDTO dto) {
        return useCaseForFind.findBySourceUrl(dto.getSourceUrl());
    }

    @Operation(summary = "Create Shortener", tags = { "shorteners" })
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "successful operation") })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid @Parameter(description = "Created shortener object") ShortenerRequestDTO dto) {
        useCaseForCreate.create(dto);
    }
}
