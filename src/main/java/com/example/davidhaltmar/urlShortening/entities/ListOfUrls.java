package com.example.davidhaltmar.urlShortening.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ListOfUrls {

    private static final Logger log = LoggerFactory.getLogger(ListOfUrls.class);

    private HashMap<String, String> list;

    public ListOfUrls(HashMap<String, String> list) {
        log.info("constructor of ListOfUrls instance");
        this.list = list;
    }

    public String addUrl(String shortUrl, String longUrl) { //saves Url - as combination of short Url (key) and long Url (value)
        log.info("addUrl called with arguments: \"" + shortUrl + "\", \"" + longUrl + "\"");
        list.putIfAbsent(shortUrl, longUrl);
        return longUrl;
    }

    public String findShortUrlIfExists(String longUrl) { //returns short Url for received long URL - if it already exists; else returns empty String for short Url (and it means that short Url has to be generated)
        log.info("findShortUrlIfExists called with argument: \"" + longUrl + "\"");
        String shortUrl = "";

        for (String url : list.keySet()) {
            if (list.get(url).equals(longUrl)) {
                log.info("findIfExists found short Url: \"" + url + "\"");
                return url;
            }
        }
        return shortUrl;
    }

    public String findLongUrl(String shortUrl) { //returns long Url for received short Url - if it exists; else returns "not found" String
        log.info("findLongUrl called with argument: \"" + shortUrl + "\"");
        String longUrl = "not found";

        for (String url : list.keySet()) {
            if (url.equals(shortUrl)) {
                return list.get(url);
            }
        }
        return longUrl;
    }

    public boolean checkShortUrlUniqueness(String shortUrl) { //checks whether generated short Url is unique; if it's not, short Url has to be generated again
        log.info("checkShortUrlUniqueness called with argument: \"" + shortUrl + "\"");

        for (String url : list.keySet()) {
            if (url.equals(shortUrl)) {
                log.info("checkShortUrlUniqueness: \"" + shortUrl + "\" is NOT unique");
                return false;
            }
        }
        log.info("checkShortUrlUniqueness: \"" + shortUrl + "\" is unique");
        return true;
    }

    public HashMap<String, String> listUrls() {
        log.info("listUrls called");

        for (String key : list.keySet()) {
            log.info(key + " : " + list.get(key) + "\n");
        }
        return list;
    }
}
