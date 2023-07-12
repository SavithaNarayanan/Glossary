package com.renewtrak.glossary.controller;

import com.renewtrak.glossary.api.GlossaryService;
import com.renewtrak.glossary.model.Glossary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GlossaryController {

    @Autowired
    GlossaryService glossaryService;

    @PostMapping("/glossaries")
    public ResponseEntity<Glossary> createEGlossary(@RequestBody Glossary glossary) {
        return new ResponseEntity<>(glossaryService.create(glossary), HttpStatus.CREATED);
    }

    @GetMapping("/glossaries")
    public ResponseEntity<List<Glossary>> getAllGlossaries() {
        return new ResponseEntity<>(glossaryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/glossaries/{id}")
    public ResponseEntity<Glossary> getEGlossaryById(@PathVariable("id") long id) {
        return glossaryService.get(id).map(glossary -> new ResponseEntity<>(glossary, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/glossaries/{id}")
    public ResponseEntity<Glossary> updateEGlossary(@PathVariable("id") long id, @RequestBody Glossary glossary) {
        return  new ResponseEntity<>(glossaryService.update(id, glossary), HttpStatus.OK);

    }

    @DeleteMapping("/glossaries/{id}")
    public ResponseEntity<HttpStatus> deleteEGlossary(@PathVariable("id") long id) {
        glossaryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/glossaries")
    public ResponseEntity<HttpStatus> deleteAllEGlossary() {
        glossaryService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
