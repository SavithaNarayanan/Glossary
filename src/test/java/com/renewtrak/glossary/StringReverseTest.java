package com.renewtrak.glossary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StringReverseTest {

    @Autowired
    StringReverse stringReverse;

    @Test
    void reverse() {
        // Case : 1 Should reverse each word within the string without altering whitespace characters
        String reversed = stringReverse.reverse("Cat and Dog");
        Assertions.assertEquals(reversed, "taC dna goD");
    }

    @Test
    void reverseStringWithPunctuation() {
        //Case :2 Should treat punctuation characters as part of the word
        String reversed = stringReverse.reverse("Hi, Does this work?");
        Assertions.assertEquals(reversed, ",iH seoD siht ?krow");
    }
}