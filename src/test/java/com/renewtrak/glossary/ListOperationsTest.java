package com.renewtrak.glossary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.LinkedList;

@SpringBootTest
class ListOperationsTest {

    @Autowired
    ListOperations listOperations;

    @Test
    void getNthElementFromTail() {
        // Case : 1 Should return the 5th element from the tail in a single pass.
        LinkedList<Integer> linkedList = new LinkedList<Integer>(Arrays.asList(2,3,4,5,6,7,8,9,10,11));
        Integer integerFromTail = listOperations.getNthElementFromTail(linkedList, 5);
        Assertions.assertEquals(integerFromTail, Integer.valueOf(7));
    }
}