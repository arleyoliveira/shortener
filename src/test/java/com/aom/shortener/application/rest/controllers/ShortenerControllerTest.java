package com.aom.shortener.application.rest.controllers;

import com.aom.shortener.application.dto.ShortenerRequestDTO;
import com.aom.shortener.application.usecase.CreateShortener;
import com.aom.shortener.application.usecase.FindShortener;
import com.aom.shortener.domain.exceptions.NotFound;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ShortenerController.class)
class ShortenerControllerTest {

    private static final String BASE_URL = "/api/v1/shorteners";

    @MockBean
    private CreateShortener createShortener;
    @MockBean
    private FindShortener findShortener;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateShortenerWithSuccessful() throws Exception {
        var shortenerRequestDTO = new ShortenerRequestDTO();

        doNothing().when(createShortener).create(shortenerRequestDTO);

        mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
                        .content("\"{\"sourceUrl\": \"www.teste.com\"}\""))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void shouldCreateShortenerWithError() throws Exception {
        var shortenerRequestDTO = new ShortenerRequestDTO();

        doNothing().when(createShortener).create(shortenerRequestDTO);

        mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldGetShortenerWithSuccessful() throws Exception {
        var sourceUrl = "www.test.com";
        var expectedUrl = "www.test.com.br";

        when(findShortener.findBySourceUrl(sourceUrl)).thenReturn(expectedUrl);

        final var mvcResult = mockMvc.perform(get(BASE_URL + "/")
                        .param("sourceUrl", sourceUrl))
                .andExpect(status().isOk())
                .andReturn();

        var content = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(content).isEqualTo(expectedUrl);
    }

    @Test
    public void shouldGetShortenerWithNotFound() throws Exception {
        var sourceUrl = "www.test.com";

        when(findShortener.findBySourceUrl(sourceUrl)).thenThrow(NotFound.class);

        mockMvc.perform(get(BASE_URL + "/")
                        .param("sourceUrl", sourceUrl))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void shouldGetShortenerWithRequestError() throws Exception {
        mockMvc.perform(get(BASE_URL + "/"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}