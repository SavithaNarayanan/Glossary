package com.renewtrak.glossary;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StringReverse {

    /**
     *
     * @param stringToReverse the string that will be reversed
     * @return the reversed string
     */
    public String reverse(String stringToReverse) {
        List<String> words = List.of(stringToReverse.split(" ")); //This splits the words in the string using " " as delimiter
        List<String> reverseWords = new ArrayList<>();  //Temporary list to store the reverse of the words
        StringBuilder reverseString = new StringBuilder();
        for(String word : words) {
            char [] wordCharacters = word.toCharArray();
            for (int i =wordCharacters.length-1 ; i>=0; i--)
            {
                reverseString.append(wordCharacters[i]);
            }
            reverseWords.add(reverseString.toString());
            reverseString.setLength(0);
        }
        return String.join(" ", reverseWords);
    }
}
