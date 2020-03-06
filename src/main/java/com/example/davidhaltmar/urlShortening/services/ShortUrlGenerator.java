package com.example.davidhaltmar.urlShortening.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlGenerator {

    private static final Logger log = LoggerFactory.getLogger(ShortUrlGenerator.class);

//    /** parses long URL into domain part and path part - short URL can be composed of domain part + randomised part */
//    public static String beautifyUrl(String longUrl) {
//        log.info("beautifyUrl called with argument: \"" + longUrl + "\"");
//        char[] longUrlChars = longUrl.toCharArray();
//        int slashCount = 0; //slashCount counts slashes in url - assumption that 3rd slash separates end of domain part of URL and start of path part of URL
//        int i; // index for position of char in charArray
//
//        for (i = 0; i < longUrlChars.length; i++) {
//            if ((longUrlChars[i]) == '/') { //find 3 slash chars in string - it separates domain part of URL from path
//                slashCount++;
//            }
//            if (slashCount == 3) break;
//        }
//
//        if (slashCount < 3) {
//            // Open Point: what to do when 3 slashes are not found in URL - how to generate random String for path part of URL in such case ? display some error ?
//        }
//        String schemeAndDomainSubstring = longUrl.substring(0, i + 1); //scheme and domain part of URL - substring of long URL
//        String pathSubstring = longUrl.substring(i + 1, longUrlChars.length); //path part of URL - substring of long URL
//        return schemeAndDomainSubstring + randomizeUrl(); //will return domain part of URL + randomized path part of URL
//    }

    /**
     * generates random short URL - output is 6 characters long
     */
    public String randomizeUrl() {
        log.info("ShortUrlGenerator randomizing URL");
        String randomizedUrl = "";

        for (int i = 0; i < 6; i++) { //for cycle to generate 6 chars long randomized URL
            char[] charsToGenerateFrom = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                    'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

            randomizedUrl += charsToGenerateFrom[(int) (Math.random() * charsToGenerateFrom.length)]; //generates random index to select from char array from allowed characters, adds selected char to String
        }
        log.info("ShortUrlGenerator generated randomized URL: \"" + randomizedUrl + "\"");
        return randomizedUrl;
    }

}
