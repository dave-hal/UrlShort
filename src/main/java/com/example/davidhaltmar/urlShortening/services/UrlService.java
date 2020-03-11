package com.example.davidhaltmar.urlShortening.services;

import com.example.davidhaltmar.urlShortening.entities.ListOfUrls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UrlService {

    @Autowired
    private ListOfUrls listOfUrls;

    @Autowired
    private ShortUrlGenerator shortUrlGenerator;

    private static final Logger log = LoggerFactory.getLogger(UrlService.class);

    /** received Long Url... try to find short Url ... otherwise generate short Url */
    public String getShortUrl(String longUrl) {
        log.debug("getShortUrl called with argument: \"" + longUrl + "\"");

        String shortUrl = listOfUrls.findShortUrlIfExists(longUrl); //first checks whether URL and its short URL already exists

        if (shortUrl == null) { // if short URL wasn't found, generate short URL and add URL+short URL
            do {
                shortUrl = shortUrlGenerator.randomizeUrl();
            } while (!listOfUrls.checkShortUrlUniqueness(shortUrl)); // if generated short URL isn't unique - repeat random generator
            listOfUrls.addUrl(shortUrl, longUrl);
        }
        return (shortUrl);
    }

    public String getLongUrl(String shortUrl) {
        log.debug("getLongUrl called with argument: " + shortUrl);
        String longUrl = listOfUrls.findLongUrl(shortUrl);

        return (longUrl);
    }

    public Map<String, String> getUrls() {
        log.debug("listing all saved values");

        return listOfUrls.listUrls();
    }

}
