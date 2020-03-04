package com.example.davidhaltmar.urlShortening.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShortUrlGeneratorTest {

    @Autowired
    private ShortUrlGenerator shortUrlGenerator;

    @Test
    void generateRandomCorrectUrlTest() {
        String result = shortUrlGenerator.randomizeUrl();
        assertEquals(6, result.length()); //Tests whether randomizeUrl returns is 6 chars long
    }
}