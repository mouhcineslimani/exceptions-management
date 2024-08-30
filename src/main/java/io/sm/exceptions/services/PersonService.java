package io.sm.exceptions.services;

import io.sm.exceptions.entities.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();
    Person getPersonById(Long id);

}
