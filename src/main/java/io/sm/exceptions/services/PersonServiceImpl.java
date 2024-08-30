package io.sm.exceptions.services;

import io.sm.exceptions.entities.Person;
import io.sm.exceptions.exceptions.PersonNotFound;
import io.sm.exceptions.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFound("Person with id : "+id+" not found"));
    }
}
