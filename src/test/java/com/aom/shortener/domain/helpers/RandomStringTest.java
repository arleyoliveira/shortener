package com.aom.shortener.domain.helpers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RandomStringTest {

    @Test
    public void shouldGenerateRandomTextWithTheSpecifiedSize() {
        var size = 10;

        var randomTextResult = RandomString.random(size);

        Assertions.assertThat(randomTextResult).hasSize(size);
    }
}