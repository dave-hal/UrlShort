package com.example.davidhaltmar.urlShortening.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ListOfUrls {

    private static final Logger log = LoggerFactory.getLogger(ListOfUrls.class);

            private Map<String, String> list;
//    private java.util.concurrent.ConcurrentMap list;

    public ListOfUrls(HashMap<String, String> list) {
//        this.list = new ConcurrentHashMap();
    this.list = list;
    }

    /**
     * saves Url - as combination of short Url (key) and long Url (value)
     */
    public String addUrl(String shortUrl, String longUrl) {
        log.debug("addUrl called with arguments: \"" + shortUrl + "\", \"" + longUrl + "\"");
        list.putIfAbsent(shortUrl, longUrl);
        return longUrl;
    }

    /**
     * returns short Url for received long URL - if it already exists; else returns empty String for short Url (and it means that short Url has to be generated)
     */
    public String findShortUrlIfExists(String longUrl) {
        log.debug("findShortUrlIfExists called with argument: \"" + longUrl + "\"");
        String shortUrl = "";

        for (String url : list.keySet()) {
            if (list.get(url).equals(longUrl)) {
                log.debug("findIfExists found short Url: \"" + url + "\"");
                return url;
            }
        }
        return shortUrl;
    }

    /**
     * returns long Url for received short Url - if it exists; else returns "not found" String
     */
    public String findLongUrl(String shortUrl) {
        log.debug("findLongUrl called with argument: \"" + shortUrl + "\"");
        String longUrl = "not found";

        for (String url : list.keySet()) {
            if (url.equals(shortUrl)) {
                return list.get(url);
            }
        }
        return longUrl;
    }

    /**
     * checks whether generated short Url is unique; if it's not, short Url has to be generated again
     */
    public boolean checkShortUrlUniqueness(String shortUrl) {
        log.debug("checkShortUrlUniqueness called with argument: \"" + shortUrl + "\"");

        for (String url : list.keySet()) {
            if (url.equals(shortUrl)) {
                log.debug("checkShortUrlUniqueness: \"" + shortUrl + "\" is NOT unique");
                return false;
            }
        }
        log.debug("checkShortUrlUniqueness: \"" + shortUrl + "\" is unique");
        return true;
    }

    /**
     * returns list of all saved combinations short Url : Url
     */
    public Map<String, String> listUrls() {
        log.debug("listUrls called");

        for (String key : list.keySet()) {
            log.debug(key + " : " + list.get(key) + "\n");
        }
        return list;
    }
}
