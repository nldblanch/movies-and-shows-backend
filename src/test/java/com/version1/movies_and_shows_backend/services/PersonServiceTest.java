package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.Cast;
import com.version1.movies_and_shows_backend.models.CastId;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.Person;
import com.version1.movies_and_shows_backend.repositories.CastRepository;
import com.version1.movies_and_shows_backend.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private CastRepository castRepository;


    @InjectMocks
    private PersonService personService;

    final Person person = CreateSamples.person();
    final List<Person> people = List.of(person);
    final Media media = CreateSamples.media().getFirst();
    final Cast cast = CreateSamples.cast(media,person);
    final CastId castId = new CastId(person.getId(),media.getId());
    final List<Cast> castList = List.of(cast);
    @Test
    public void getAllPeopleTest()
    {

        when(personRepository.findAll()).thenReturn(people);

        List<Person> result = personService.getAllPersons();
        assertEquals(result,people);
    }

    @Test
    public void getPersonByNameTest()
    {
        when(personRepository.findByNameIgnoreCase("Peter Robbins")).thenReturn(Optional.of(person));
        Person result = personService.getByName("Peter Robbins");
        assertEquals(result, person);

    }

    @Test
    public void getPersonByNameNotFound()
    {

        Person result = personService.getByName("Nathan");
        assertNull(result);
    }

    @Test
    public void getCastByRoleTest()
    {
        when(castRepository.findByRoleIgnoreCase("Actor")).thenReturn(castList);
        List<Cast> result = personService.getByRole("Actor");

        assertEquals(castList, result);
    }

    @Test
    public void getCastByRoleNotFoundTest()
    {
        List<Cast> result = personService.getByRole("Director");

        assertEquals(new ArrayList<>(),result);
    }

    @Test
    public void getPersonByCharacterTest()
    {
        when(castRepository.findByCharacterIgnoreCase("Charlie Brown")).thenReturn(Optional.of(cast));
        Person result = personService.getPersonByCharacterName("Charlie Brown");

        assertEquals(cast.getPerson(), result);
    }

    @Test
    public void getPersonByCharacterNotFoundTest()
    {
        Person result = personService.getPersonByCharacterName("Chicken Little");

        assertNull(result);
    }

    @Test
    public void getMediaByPersonTest() {
        when(castRepository.findByPerson(person)).thenReturn(castList);
        List<Media> mediaList = castList.stream()
                .map(Cast::getMedia)
                .distinct()
                .toList();
        List<Media> result = personService.getMediaByPerson(person);

        assertEquals(mediaList, result);
    }

    @Test
    public void getMediaByPersonNotFoundTest() {
        List<Media> result = personService.getMediaByPerson(new Person());
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void getPersonByIdTest() {
        when(personRepository.findById(1)).thenReturn(Optional.of(person));
        Person result = personService.getById(1);
        assertEquals(person, result);
    }

    @Test
    public void getPersonByIdNotFoundTest() {
        Person result = personService.getById(2);
        assertNull(result);
    }

    @Test
    public void getCharacterByPersonTest() {
        when(castRepository.findByPerson(person)).thenReturn(castList);
        List<String> characters = castList.stream()
                .map(Cast::getCharacter)
                .distinct()
                .toList();
        List<String> result = personService.getCharactersByPerson(person);
        assertEquals(characters, result);
    }



}
