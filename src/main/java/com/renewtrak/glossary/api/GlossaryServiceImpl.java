package com.renewtrak.glossary.api;

import com.renewtrak.glossary.exception.ApiException;
import com.renewtrak.glossary.mapper.GlossaryMapper;
import com.renewtrak.glossary.entity.EGlossary;
import com.renewtrak.glossary.message.MessageConstants;
import com.renewtrak.glossary.message.MessageUtil;
import com.renewtrak.glossary.model.Glossary;
import com.renewtrak.glossary.repository.GlossaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GlossaryServiceImpl implements GlossaryService {

    final GlossaryRepository glossaryRepository;
    final GlossaryMapper glossaryMapper;

    @Autowired
    public GlossaryServiceImpl(GlossaryRepository glossaryRepository, GlossaryMapper glossaryMapper) {
        this.glossaryRepository = glossaryRepository;
        this.glossaryMapper = glossaryMapper;
    }

    @Override
    public Glossary create(Glossary glossary) {
        Optional<EGlossary> eGlossary = glossaryRepository.findByTermEqualsIgnoreCase(glossary.getTerm());
        if(eGlossary.isPresent()){
            //throw exception if the term is already defined
            throw new ApiException(MessageUtil.get().getMessage(MessageConstants.DUPLICATE_TEM));
        }
        EGlossary _glossary = glossaryRepository
                .save(new EGlossary(glossary.getTerm(), glossary.getDefinition()));
        return glossaryMapper.entityToModel(_glossary);
    }

    @Override
    public Glossary update(Long id, Glossary glossary) {
        Optional<EGlossary> glossaryData = glossaryRepository.findById(id);
        if (glossaryData.isPresent()) {
            Optional<EGlossary> glossaryWithSameTermExists = glossaryRepository.findByTermEqualsIgnoreCaseAndIdIsNot(glossary.getTerm(), glossaryData.get().getId());
            if(glossaryWithSameTermExists.isEmpty()) {
                EGlossary _glossary = glossaryData.get();
                _glossary.setTerm(glossary.getTerm());
                _glossary.setDefinition(glossary.getDefinition());
                return glossaryMapper.entityToModel(glossaryRepository.save(_glossary));
            } else {
                throw new ApiException(MessageUtil.get().getMessage(MessageConstants.DUPLICATE_TEM));
            }
        } else {
            throw new ApiException(MessageUtil.get().getMessage(MessageConstants.ID_NOT_FOUND));
        }
    }

    @Override
    public void delete(Long id) {
        Optional<EGlossary> glossaryData = glossaryRepository.findById(id);
        if (glossaryData.isPresent()) {
            glossaryRepository.deleteById(id);
        } else
            throw new ApiException(MessageUtil.get().getMessage(MessageConstants.ID_NOT_FOUND));

    }

    @Override
    public void deleteAll() {
        glossaryRepository.deleteAll();
    }

    @Override
    public Optional<Glossary> get(Long id) {
        Optional<EGlossary> glossaryData = glossaryRepository.findById(id);
        return glossaryData.map(glossaryMapper::entityToModel);
    }

    @Override
    public List<Glossary> getAll() {
        List<EGlossary> glossaries = new ArrayList<>(glossaryRepository.findAll());
        List<Glossary> glossaryList = new ArrayList<>(glossaries.stream().map(
                glossaryMapper::entityToModel
        ).toList());
        glossaryList.sort(Comparator.comparing(Glossary::getTerm, String.CASE_INSENSITIVE_ORDER));
        return glossaryList;
    }
}
