package com.renewtrak.glossary.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "glossary")
public class EGlossary {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_Sequence")
    @SequenceGenerator(initialValue = 5, name = "ID_Sequence", sequenceName = "ID_Sequence_PK")
    private Long id;

    @Column(name = "term")
    private String term;

    @Column(name = "definition")
    private String definition;

    public EGlossary(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public EGlossary() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
