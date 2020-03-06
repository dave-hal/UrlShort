package com.example.davidhaltmar.urlShortening.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShortUrlGeneratorTest {

    @Autowired
    private ShortUrlGenerator shortUrlGenerator;

    /** Tests whether randomizeUrl returns 6 chars long String */
    @Test
    void generateRandomCorrectUrlTest() {
        //when
        String result = shortUrlGenerator.randomizeUrl();

        //then
        assertEquals(6, result.length());
    }
}