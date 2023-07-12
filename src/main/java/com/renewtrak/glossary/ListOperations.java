package com.renewtrak.glossary;

import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class ListOperations {

    /**
     *
     * @param integerLinkedList integer linked list
     * @param n n is the index from tail
     * @return the nth element from tail
     */
    public Integer getNthElementFromTail(LinkedList<Integer> integerLinkedList, int n) {
        if (n > integerLinkedList.size()) {
            return -1;
        }
        for (int i = integerLinkedList.size() - 1; i >= 0; i--) {
            if (i == n) {
                return integerLinkedList.get(i);
            }
        }
        return -1;
    }
}
