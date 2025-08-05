package com.version1.movies_and_shows_backend.controllers;

import com.version1.movies_and_shows_backend.models.Cast;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.Person;
import com.version1.movies_and_shows_backend.services.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    // GET /people/{id}
    @Transactional
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable int id) {
        return personService.getById(id);

    }

    // GET /people/{id}/media
    @Transactional
    @GetMapping("/{id}/media")
    public List<Media> getMediaByPerson(@PathVariable int id) {
        return personService.getMediaByPerson(personService.getById(id)); // probably not the best way to do this, but it works for now
    }

    @Transactional
    @GetMapping("/{id}/character")
    public List<String> getPersonByCharacterName(@PathVariable int id) {
        return personService.getCharactersByPerson(personService.getById(id));

    }

    @Transactional
    @GetMapping("/{role}")
    public List<Cast> getPersonsByRole(@PathVariable String role) {
        return personService.getByRole(role);
    }


}
