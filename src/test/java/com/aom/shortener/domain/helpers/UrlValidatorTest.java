package com.aom.shortener.domain.helpers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UrlValidatorTest {

    @Test
    public void shouldReturnTrueWhenUrlIsValid() {
        var url = "http://test.com";

        var result = UrlValidator.isValid(url);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenUrlIsNotValid() {
        var url = "test.com";

        var result = UrlValidator.isValid(url);

        assertFalse(result);
    }

}