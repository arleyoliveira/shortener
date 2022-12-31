package com.aom.shortener.domain.entities;

import com.aom.shortener.domain.exceptions.NotFound;
import com.aom.shortener.domain.exceptions.ValidateException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ShortenerTest {

    @Test
    public void shouldCreateShortenerEntityWithSuccessful() {
        //Arrange
        var sourceUrlExpected = "http://www.test.com";
        var shortenerExpected = "https://link.to/1234";

        var result = new Shortener(sourceUrlExpected, shortenerExpected);

        Assertions.assertThat(result.getSourceUrl()).isEqualTo(sourceUrlExpected);
        Assertions.assertThat(result.getShortenedUrl()).isEqualTo(shortenerExpected);
    }

    @Test
    public void shouldThrowExceptionOnCreationWhenShortenedUrlIsEmpty() {
        //Arrange
        var sourceUrlExpected = "http://www.test.com";
        var shortenerExpected = "";

        assertThrows(ValidateException.class, () -> {
            new Shortener(sourceUrlExpected, shortenerExpected);
        });
    }

    @Test
    public void shouldThrowExceptionOnCreationWhenSourceUrlIsEmpty() {
        //Arrange
        var sourceUrlExpected = "";
        var shortenerExpected = "https://link.to/1234";

        assertThrows(ValidateException.class, () -> {
            new Shortener(sourceUrlExpected, shortenerExpected);
        });
    }

    @Test
    public void shouldThrowExceptionOnCreationWhenShortenedUrlHasLengthLessThanFive() {
        //Arrange
        var sourceUrlExpected = "http://www.test.com";
        var shortenerExpected = "http";

        assertThrows(ValidateException.class, () -> {
            new Shortener(sourceUrlExpected, shortenerExpected);
        });
    }

    @Test
    public void shouldThrowExceptionOnCreationWhenShortenedUrlHasLengthGreaterThanTwenty() {
        //Arrange
        var sourceUrlExpected = "http://www.test.com";
        var shortenerExpected = "http://link.to/123456789abcdefgh220010";

        assertThrows(ValidateException.class, () -> {
            new Shortener(sourceUrlExpected, shortenerExpected);
        });
    }

    @Test
    public void shouldThrowExceptionWhenSourceUrlIsInvalid() {
        //Arrange
        var sourceUrlExpected = "www.test.com";
        var shortenerExpected = "http://link.to/1020";

        assertThrows(ValidateException.class, () -> {
            new Shortener(sourceUrlExpected, shortenerExpected);
        });
    }

}