package com.example.davidhaltmar.urlShortening.controllers;

import com.example.davidhaltmar.urlShortening.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UrlShorteningRestController {

    @Autowired
    private UrlService urlService;

    /**
     * mapping for obtaining short Url (long Url (value) as input)
     */
    @GetMapping("/getshort")
    public String getShortUrl(@RequestParam(required = true) String url) {
        return urlService.getShortUrl(url);
    }

    /**
     * mapping for obtaining long Url (short Url (key) as input)
     */
    @GetMapping("/getlong")
    public ResponseEntity<String> getLongUrl(@RequestParam(required = true) String url) {
        return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .header("Location", urlService.getLongUrl(url))
                    .build();
    }

    /**
     * mapping for obtaining list of Urls (short URL : long URL)
     */
    @GetMapping("/geturls")
    public Map<String, String> urls() {
        return urlService.getUrls();
    }

}
