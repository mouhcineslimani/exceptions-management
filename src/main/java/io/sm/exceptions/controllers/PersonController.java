package io.sm.exceptions.controllers;

import io.sm.exceptions.entities.Person;
import io.sm.exceptions.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(personService.getPersonById(id), HttpStatus.OK);
    }
}
