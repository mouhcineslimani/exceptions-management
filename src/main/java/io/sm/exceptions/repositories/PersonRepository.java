package io.sm.exceptions.repositories;

import io.sm.exceptions.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
