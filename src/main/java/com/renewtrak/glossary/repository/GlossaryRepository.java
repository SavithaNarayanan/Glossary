package com.renewtrak.glossary.repository;

import com.renewtrak.glossary.entity.EGlossary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GlossaryRepository extends JpaRepository<EGlossary, Long> {
    Optional<EGlossary> findByTermEqualsIgnoreCase(String term);

    Optional<EGlossary> findByTermEqualsIgnoreCaseAndIdIsNot(String term, Long id);
}
