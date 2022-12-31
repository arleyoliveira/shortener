package com.aom.shortener.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortenerRequestDTO {

    @NotBlank(message = "Source is required")
    private String sourceUrl;
}
