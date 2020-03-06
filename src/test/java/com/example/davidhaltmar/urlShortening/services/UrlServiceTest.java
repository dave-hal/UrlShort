package com.example.davidhaltmar.urlShortening.services;

import com.example.davidhaltmar.urlShortening.entities.ListOfUrls;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UrlServiceTest {

    @Autowired
    private UrlService urlService;

    @Autowired
    private ListOfUrls listOfUrls;

    @Autowired
    private ShortUrlGenerator shortUrlGenerator;

    /** test whether returns short url - for existing long URL - instead of generating and saving new one */
    @Test
    void returnsSavedShortUrlForExistingLongUrlTest() {
        String result1 = urlService.getShortUrl("https://hackernoon.com/coding-is-more-creative-than-you-might-think-ws803wv3");
        String result2 = urlService.getShortUrl("https://hackernoon.com/coding-is-more-creative-than-you-might-think-ws803wv3");
        assertEquals(result1, result2); //checks short URL in a result of 1-st call versus result of 2-nd call
    }


    /** test whether returns correct long Url - for entered short Url */
    @Test
    void returnsCorrectLongUrlTest() {
        //given
        String longUrl = "https://hackernoon.com/why-software-testing-is-so-important-yp5932mm";

        //when
        String shortUrl = urlService.getShortUrl(longUrl);
        String result = urlService.getLongUrl(shortUrl);

        //then
        assertEquals(longUrl, result);
    }

}