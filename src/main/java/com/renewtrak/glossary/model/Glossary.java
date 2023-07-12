package com.renewtrak.glossary.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Glossary {

    private Long id;

    private String term;

    private String definition;

    public Glossary(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }
}
