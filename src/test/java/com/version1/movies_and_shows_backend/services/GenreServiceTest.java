package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.dtos.GenreDTO;
import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.mappers.GenreMapper;
import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.repositories.GenreRepository;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
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
public class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private MediaRepository mediaRepository;

    @InjectMocks
    private GenreService  genreService;

    @InjectMocks
    private MediaService mediaService;

    final List<Genre> genres = CreateSamples.genres();

    final Media media = CreateSamples.media().getFirst();
    @Test
    public void getAllGenresTest()
    {
        when(genreRepository.findAll()).thenReturn(genres);

        List<GenreDTO> result = genreService.getAllGenres(false);

        assertEquals(genres.stream().map(GenreMapper::toDTO).toList(),result);
    }

    @Test
    public void getAllGenresNotFoundTest()
    {

        List<GenreDTO> result = genreService.getAllGenres(false);

        assertEquals(new ArrayList<>(),result);
    }

    @Test
    public void getGenreByNameTest() {
        Genre genre = new Genre("comedy");
        when(genreRepository.findByNameIgnoreCase("comedy")).thenReturn(Optional.of(genre));

        GenreDTO result = genreService.getGenreByName("comedy");

        assertEquals(new GenreDTO(genre.getId(), genre.getName()), result);
    }
    @Test
    public void getGenreByNameNotFoundTest() {
//        String nonExistentGenre = "SciFantasy";
//        when(genreRepository.findByNameIgnoreCase(nonExistentGenre)).thenReturn(Optional.empty());

        GenreDTO result = genreService.getGenreByName("cheese");

        assertNull(result);
    }


    @Test
    public void getMediaByGenreTest(){
        List<Media> medias = List.of(media);
        when(mediaRepository.findByGenres_NameIgnoreCase("comedy")).thenReturn(medias);
        List<Media> result = mediaService.getByGenre("comedy");
        assertEquals(result, medias);

        when(mediaRepository.findByGenres_NameIgnoreCase("drama")).thenReturn(medias);
        result = mediaService.getByGenre("drama");
        assertEquals(result, medias);

    }

    @Test
    public void getMediaByGenreNotFoundTest()
    {
        List<Media> result = mediaService.getByGenre("cheese");
        assertEquals(new ArrayList<Media>(), result);

        result = mediaService.getByGenre("romance");
        assertEquals(new ArrayList<Media>(), result);
    }
}
