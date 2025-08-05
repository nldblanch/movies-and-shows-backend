package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.dtos.GenreDTO;
import com.version1.movies_and_shows_backend.dtos.MediaDTO;
import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.mappers.GenreMapper;
import com.version1.movies_and_shows_backend.models.*;
import com.version1.movies_and_shows_backend.repositories.GenreRepository;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
import com.version1.movies_and_shows_backend.repositories.GenreStatsRepository;
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
    private GenreStatsRepository genreStatsRepository;

    @Mock
    private MediaRepository mediaRepository;

    @InjectMocks
    private GenreService  genreService;

    final List<Genre> genres = CreateSamples.genres();

    final Media media = CreateSamples.media().getFirst();
    @Test
    public void getAllGenresTest()
    {
        when(genreRepository.findAll()).thenReturn(genres);
        when(genreStatsRepository.findAll()).thenReturn(new ArrayList<>());
        List<GenreDTO> result = genreService.getAllGenres();

        assertEquals(genres.stream().map(GenreMapper::toDTO).toList(),result);
    }

    @Test
    public void getAllGenresNotFoundTest()
    {
        when(genreRepository.findAll()).thenReturn(new ArrayList<>());
        when(genreStatsRepository.findAll()).thenReturn(new ArrayList<>());
        List<GenreDTO> result = genreService.getAllGenres();

        assertEquals(new ArrayList<>(),result);
    }

    @Test
    public void getGenreByNameTest() {
        Genre genre = new Genre(1, "comedy");
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
        List<Media> mediaList = List.of(media);
        when(mediaRepository.findByGenres_NameIgnoreCase("comedy")).thenReturn(mediaList);
        List<MediaDTO> result = genreService.getMediaByGenre("comedy");

        MediaDTO expected = mediaList.stream().map(MediaDTO::new).toList().getFirst();
        MediaDTO actual = result.getFirst();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getAgeCert(), actual.getAgeCert());
        assertEquals(expected.getReleaseYear(), actual.getReleaseYear());
        assertEquals(expected.getRuntime(), actual.getRuntime());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getImdbId(), actual.getImdbId());
        assertEquals(expected.getImdbScore(), actual.getImdbScore());
        assertEquals(expected.getImdbVotes(), actual.getImdbVotes());
        assertEquals(expected.getTmdbPopularity(), actual.getTmdbPopularity());
        assertEquals(expected.getTmdbScore(), actual.getTmdbScore());
        assertEquals(expected.getSeasons(), actual.getSeasons());

        // Compare genres
        assertEquals(
                expected.getGenres().stream().map(GenreDTO::getName).toList(),
                actual.getGenres().stream().map(GenreDTO::getName).toList()
        );

        // Compare sites
        assertEquals(
                expected.getSites().stream().map(Site::getName).toList(),
                actual.getSites().stream().map(Site::getName).toList()
        );

        // Compare production countries
        assertEquals(
                expected.getProductionCountries().stream().map(ProductionCountry::getName).toList(),
                actual.getProductionCountries().stream().map(ProductionCountry::getName).toList()
        );

        // Compare cast characters
        assertEquals(
                expected.getCast().stream().map(Cast::getCharacter).toList(),
                actual.getCast().stream().map(Cast::getCharacter).toList()
        );

        // Compare cast roles
        assertEquals(
                expected.getCast().stream().map(Cast::getRole).toList(),
                actual.getCast().stream().map(Cast::getRole).toList()
        );

        // Compare cast person names
        assertEquals(
                expected.getCast().stream().map(c -> c.getPerson().getName()).toList(),
                actual.getCast().stream().map(c -> c.getPerson().getName()).toList()
        );

    }

    @Test
    public void getMediaByGenreNotFoundTest()
    {
        List<MediaDTO> result = genreService.getMediaByGenre("cheese");
        assertEquals(new ArrayList<Media>(), result);

        result = genreService.getMediaByGenre("romance");
        assertEquals(new ArrayList<Media>(), result);
    }


    @Test
    public void getTop10MoviesByGenreTest() {
        List<Media> mediaList = List.of(media);
        when(mediaRepository.findTop10ByGenres_NameIgnoreCaseAndTypeOrderByImdbScoreDesc("comedy", "movie")).thenReturn(mediaList);

        List<MediaDTO> result = genreService.getTop10MoviesByGenre("comedy");

        MediaDTO expected = mediaList.stream().map(MediaDTO::new).toList().getFirst();
        MediaDTO actual = result.getFirst();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getAgeCert(), actual.getAgeCert());
        assertEquals(expected.getReleaseYear(), actual.getReleaseYear());
        assertEquals(expected.getRuntime(), actual.getRuntime());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getImdbId(), actual.getImdbId());
        assertEquals(expected.getImdbScore(), actual.getImdbScore());
        assertEquals(expected.getImdbVotes(), actual.getImdbVotes());
        assertEquals(expected.getTmdbPopularity(), actual.getTmdbPopularity());
        assertEquals(expected.getTmdbScore(), actual.getTmdbScore());
        assertEquals(expected.getSeasons(), actual.getSeasons());

        // Compare genres
        assertEquals(
                expected.getGenres().stream().map(GenreDTO::getName).toList(),
                actual.getGenres().stream().map(GenreDTO::getName).toList()
        );

        // Compare sites
        assertEquals(
                expected.getSites().stream().map(Site::getName).toList(),
                actual.getSites().stream().map(Site::getName).toList()
        );

        // Compare production countries
        assertEquals(
                expected.getProductionCountries().stream().map(ProductionCountry::getName).toList(),
                actual.getProductionCountries().stream().map(ProductionCountry::getName).toList()
        );

        // Compare cast characters
        assertEquals(
                expected.getCast().stream().map(Cast::getCharacter).toList(),
                actual.getCast().stream().map(Cast::getCharacter).toList()
        );

        // Compare cast roles
        assertEquals(
                expected.getCast().stream().map(Cast::getRole).toList(),
                actual.getCast().stream().map(Cast::getRole).toList()
        );

        // Compare cast person names
        assertEquals(
                expected.getCast().stream().map(c -> c.getPerson().getName()).toList(),
                actual.getCast().stream().map(c -> c.getPerson().getName()).toList()
        );
    }
    @Test
    public void getTop10MoviesByGenreNotFoundTest() {
        List<MediaDTO> result = genreService.getTop10MoviesByGenre("cheese");
        assertEquals(new ArrayList<Media>(), result);
        result = genreService.getTop10MoviesByGenre("romance");
        assertEquals(new ArrayList<Media>(), result);
    }
    @Test
    public void getTop10ShowsByGenreTest() {
        List<Media> mediaList = List.of(media);
        when(mediaRepository.findTop10ByGenres_NameIgnoreCaseAndTypeOrderByImdbScoreDesc("comedy", "show")).thenReturn(mediaList);

        List<MediaDTO> result = genreService.getTop10ShowsByGenre("comedy");

        MediaDTO expected = mediaList.stream().map(MediaDTO::new).toList().getFirst();
        MediaDTO actual = result.getFirst();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getAgeCert(), actual.getAgeCert());
        assertEquals(expected.getReleaseYear(), actual.getReleaseYear());
        assertEquals(expected.getRuntime(), actual.getRuntime());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getImdbId(), actual.getImdbId());
        assertEquals(expected.getImdbScore(), actual.getImdbScore());
        assertEquals(expected.getImdbVotes(), actual.getImdbVotes());
        assertEquals(expected.getTmdbPopularity(), actual.getTmdbPopularity());
        assertEquals(expected.getTmdbScore(), actual.getTmdbScore());
        assertEquals(expected.getSeasons(), actual.getSeasons());

        // Compare genres
                assertEquals(
                        expected.getGenres().stream().map(GenreDTO::getName).toList(),
                        actual.getGenres().stream().map(GenreDTO::getName).toList()
                );

        // Compare sites
                assertEquals(
                        expected.getSites().stream().map(Site::getName).toList(),
                        actual.getSites().stream().map(Site::getName).toList()
                );

        // Compare production countries
                assertEquals(
                        expected.getProductionCountries().stream().map(ProductionCountry::getName).toList(),
                        actual.getProductionCountries().stream().map(ProductionCountry::getName).toList()
                );

        // Compare cast characters
                assertEquals(
                        expected.getCast().stream().map(Cast::getCharacter).toList(),
                        actual.getCast().stream().map(Cast::getCharacter).toList()
                );

        // Compare cast roles
                assertEquals(
                        expected.getCast().stream().map(Cast::getRole).toList(),
                        actual.getCast().stream().map(Cast::getRole).toList()
                );

        // Compare cast person names
                assertEquals(
                        expected.getCast().stream().map(c -> c.getPerson().getName()).toList(),
                        actual.getCast().stream().map(c -> c.getPerson().getName()).toList()
                );



    }
    @Test
    public void getTop10ShowsByGenreNotFoundTest() {
        List<MediaDTO> result = genreService.getTop10ShowsByGenre("cheese");
        assertEquals(new ArrayList<Media>(), result);
        result = genreService.getTop10ShowsByGenre("romance");
        assertEquals(new ArrayList<Media>(), result);

    }
}
