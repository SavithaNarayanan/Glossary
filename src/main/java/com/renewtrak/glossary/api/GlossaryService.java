package com.renewtrak.glossary.api;

import com.renewtrak.glossary.model.Glossary;

import java.util.List;
import java.util.Optional;

public interface GlossaryService {

    Glossary create(Glossary glossary);
    Glossary update(Long id, Glossary glossary);
    void delete(Long id);

    void deleteAll();
    Optional<Glossary> get(Long id);

    List<Glossary> getAll();
}
