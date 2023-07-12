package com.renewtrak.glossary.repository;

import com.renewtrak.glossary.entity.EGlossary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlossaryRepository extends JpaRepository<EGlossary, Long> {
}
