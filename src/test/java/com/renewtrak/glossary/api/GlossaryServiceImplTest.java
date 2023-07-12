package com.renewtrak.glossary.api;

import com.renewtrak.glossary.exception.ApiException;
import com.renewtrak.glossary.message.MessageConstants;
import com.renewtrak.glossary.message.MessageUtil;
import com.renewtrak.glossary.model.Glossary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GlossaryServiceImplTest {

    @Autowired
    GlossaryService glossaryService;

    @Test
    void create() {
        Glossary glossary = glossaryService.create(new Glossary("synonym", "A synonym is a word, morpheme or phrase which has the exact same meaning, or a very similar meaning, to another word."));
        Assertions.assertNotNull(glossary);
        Assertions.assertNotNull(glossary.getId());
        Assertions.assertEquals(glossary.getTerm() ,  "synonym");
        Assertions.assertEquals(glossary.getDefinition() ,  "A synonym is a word, morpheme or phrase which has the exact same meaning, or a very similar meaning, to another word.");
    }

    @Test
    void createDuplicate() {
        Glossary glossary = glossaryService.create(new Glossary("synonym", "A synonym is a word, morpheme or phrase which has the exact same meaning, or a very similar meaning, to another word."));
        Assertions.assertNotNull(glossary);
        Assertions.assertNotNull(glossary.getId());
        Assertions.assertEquals(glossary.getTerm() ,  "synonym");
        try {
            glossaryService.create(new Glossary("synonym", "A synonym is a word, morpheme or phrase which has the exact same meaning, or a very similar meaning, to another word."));
            Assertions.fail();  //fail if exception not thrown
        }
        catch (ApiException apiException){
            Assertions.assertEquals(apiException.getMessage(), MessageUtil.get().getMessage(MessageConstants.DUPLICATE_TEM));
        }
    }

    @Test
    void update() {
        Glossary glossary = glossaryService.create(new Glossary("synonym", "A synonym is a word, morpheme or phrase which has the exact same meaning, or a very similar meaning, to another word."));
        Glossary glossaryUpdated = glossaryService.update(glossary.getId(), new Glossary("synonym", "Meaning Updated."));

        Assertions.assertNotNull(glossaryUpdated);
        Assertions.assertEquals(glossaryUpdated.getId(), glossary.getId());
        Assertions.assertEquals(glossaryUpdated.getTerm() ,  glossary.getTerm());
        Assertions.assertEquals(glossaryUpdated.getDefinition() ,  "Meaning Updated.");
    }

    @Test
    void updateAndCreateDuplicate() {
        Glossary glossary1 = glossaryService.create(new Glossary("synonym", "A synonym is a word, morpheme or phrase which has the exact same meaning, or a very similar meaning, to another word."));
        Glossary glossary2 = glossaryService.create(new Glossary("new_synonym", "A synonym is a word, morpheme or phrase which has the exact same meaning, or a very similar meaning, to another word."));
        try {
            Glossary glossary2UpdatedAsDuplicateOdGlossary1 = glossaryService.update(glossary2.getId(), new Glossary("synonym", "Meaning Updated."));
            Assertions.fail();  //fail if exception not thrown
        }
        catch (ApiException apiException){
            Assertions.assertEquals(apiException.getMessage(), MessageUtil.get().getMessage(MessageConstants.DUPLICATE_TEM));
        }

    }

    @Test
    void updateWithWrongId() {
        try{
            glossaryService.update(100L, new Glossary("synonym", "Meaning Updated."));
            Assertions.fail();  //fail if exception not thrown
        }
        catch (ApiException apiException){
            Assertions.assertEquals(apiException.getMessage(), MessageUtil.get().getMessage(MessageConstants.ID_NOT_FOUND));
        }

    }

    @Test
    void delete() {
        Glossary glossary = glossaryService.create(new Glossary("synonym", "A synonym is a word, morpheme or phrase which has the exact same meaning, or a very similar meaning, to another word."));
        Optional<Glossary> glossaryFound = glossaryService.get(glossary.getId());
        Assertions.assertTrue(glossaryFound.isPresent());
        glossaryService.delete(glossary.getId());
        glossaryFound = glossaryService.get(glossary.getId());
        Assertions.assertFalse(glossaryFound.isPresent());
    }

    @Test
    void deleteNonExistingId() {
        glossaryService.create(new Glossary("synonym", "A synonym is a word, morpheme or phrase which has the exact same meaning, or a very similar meaning, to another word."));
        try{
            glossaryService.delete(100L);
            Assertions.fail(); //fail if exception not thrown
        }catch (ApiException apiException){
            Assertions.assertEquals(apiException.getMessage(), MessageUtil.get().getMessage(MessageConstants.ID_NOT_FOUND));
        }
    }

    @Test
    void deleteAll() {
        glossaryService.create(new Glossary("synonym", "A synonym is a word, morpheme or phrase which has the exact same meaning, or a very similar meaning, to another word."));
        List<Glossary> glossaryFound = glossaryService.getAll();
        Assertions.assertTrue(glossaryFound.size() > 0);
        glossaryService.deleteAll();
        glossaryFound = glossaryService.getAll();
        Assertions.assertEquals(0, glossaryFound.size());
    }

    @Test
    void get() {
        Glossary glossary = glossaryService.create(new Glossary("synonym", "A synonym is a word, morpheme or phrase which has the exact same meaning, or a very similar meaning, to another word."));
        Optional<Glossary> glossaryFound = glossaryService.get(glossary.getId());
        Assertions.assertTrue(glossaryFound.isPresent());
    }

    @Test
    void getAll() {
        glossaryService.create(new Glossary("synonym", "A synonym is a word, morpheme or phrase which has the exact same meaning, or a very similar meaning, to another word."));
        List<Glossary> glossaryFound = glossaryService.getAll();
        Assertions.assertTrue(glossaryFound.size() > 0);
    }

}