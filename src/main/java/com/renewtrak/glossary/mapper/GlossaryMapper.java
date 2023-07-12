package com.renewtrak.glossary.mapper;
import com.renewtrak.glossary.entity.EGlossary;
import com.renewtrak.glossary.model.Glossary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public abstract class GlossaryMapper {

    @Mapping(target = "term", source = "eGlossary.term")
    @Mapping(target = "definition", source = "eGlossary.definition")
    @Mapping(target = "id", source = "eGlossary.id")
    public abstract Glossary entityToModel(EGlossary eGlossary);
}
