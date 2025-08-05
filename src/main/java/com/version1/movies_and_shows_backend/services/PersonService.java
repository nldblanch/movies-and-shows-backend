package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.models.Cast;
import com.version1.movies_and_shows_backend.models.CastId;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.Person;
import com.version1.movies_and_shows_backend.repositories.CastRepository;
import com.version1.movies_and_shows_backend.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    // cast and person have been combined into 1 service as they are so closely related

    @Autowired
    CastRepository castRepository;

    @Autowired
    PersonRepository personRepository;

    public Cast getById(CastId id) { return castRepository.findById(id).orElse(null);}



    public List<Cast> getByPerson(Person person) { return castRepository.findByPerson(person);}


    public List<Cast> getByRole (String role) { return castRepository.findByRoleIgnoreCase(role);}


    //save...


    // get person by character
    public Person getPersonByCharacterName(String character) {
        Cast cast = castRepository.findByCharacterIgnoreCase(character).orElse(null);
        return cast != null ? cast.getPerson() : null;
    }
    
    // get all person
    public List<Person> getAllPersons() { return personRepository.findAll(); }

    // get person by name
    public Person getByName(String name) {
        return personRepository.findByNameIgnoreCase(name).orElse(null);
    }

    // get media by person
    public List<Media> getMediaByPerson(Person person) {
        return castRepository.findByPerson(person).stream()
                .map(Cast::getMedia)
                .distinct()
                .toList();
    }

    // get by person id
    public Person getById(int id) {
        return personRepository.findById(id).orElse(null);
    }

    // get Characters by person
    public List<String> getCharactersByPerson(Person person) {
        return castRepository.findByPerson(person).stream()
                .map(Cast::getCharacter)
                .distinct()
                .toList();
    }

}
