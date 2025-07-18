package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByNameIgnoreCase(String name);

}
