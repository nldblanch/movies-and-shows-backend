package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.Cast;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.Person;
import com.version1.movies_and_shows_backend.repositories.CastRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MediaServiceTest {

    @Mock
    private MediaRepository mediaRepository;
    @Mock
    private CastRepository castRepository;

    @InjectMocks
    private MediaService mediaService;






    final Media media = CreateSamples.media().getFirst();
    final Person person = CreateSamples.person();
    final Cast cast = CreateSamples.cast(media,person);

    @Test
    public void getMediaByIdTest() {

        when(mediaRepository.findById("tm1300")).thenReturn(Optional.of(media));

        Media result = mediaService.getMediaById("tm1300");
        assertEquals(result, media);

    }

    @Test
    public void getMediaByIdNotFoundTest(){
        Media result = mediaService.getMediaById("2");
        assertNull(result);
    }

    @Test
    public void getAllMediaTest(){
        List<Media> medias = List.of(media);
        when(mediaRepository.findAll()).thenReturn(medias);

        List<Media> result = mediaService.getAllMedia();
        assertEquals(result, medias);

    }

    @Test
    public  void getByTitleTest()
    {
        when(mediaRepository.findFirstByTitleIgnoreCase("A Charlie Brown Christmas")).thenReturn(Optional.of(media));

        Media result = mediaService.getByTitle("A Charlie Brown Christmas");
        assertEquals(result, media);
    }

    @Test
    public void getByTitleNotFoundTest()
    {
        Media result = mediaService.getByTitle("Boss Baby Back In Business");
        assertNull(result);
    }

    @Test
    public void getCastByMediaTest() {

        when(castRepository.findByMedia(media)).thenReturn(List.of(cast));
        List<Cast> result = mediaService.getCastByMedia(media);
        assertEquals(result, List.of(cast));
    }

    @Test
    public void getCastByMediaNotFoundTest() {
        List<Cast> result = mediaService.getCastByMedia(media);
        assertEquals(result, new ArrayList<>());
    }

    @Test
    public void getByYearTest() {
        List<Media> medias = List.of(media);
        when(mediaRepository.findByReleaseYear(1965)).thenReturn(medias);

        List<Media> result = mediaService.getByYear(1965);
        assertEquals(result, medias);
    }
    @Test
    public void getByYearNotFoundTest() {
        List<Media> result = mediaService.getByYear(2023);
        assertEquals(result, new ArrayList<>());
    }

    @Test
    public void getByTypeTest() {
        List<Media> medias = List.of(media);
        when(mediaRepository.findByTypeIgnoreCase("MOVIE")).thenReturn(medias);

        List<Media> result = mediaService.getByType("MOVIE");
        assertEquals(result, medias);
    }

    @Test
    public void getByTypeNotFoundTest() {
        List<Media> result = mediaService.getByType("TV");
        assertEquals(result, new ArrayList<>());
    }
    @Test
    public void getByAgeCertificationTest() {
        List<Media> medias = List.of(media);
        when(mediaRepository.findByAgeCertIgnoreCase("G")).thenReturn(medias);

        List<Media> result = mediaService.getByAgeCertification("G");
        assertEquals(result, medias);
    }
    @Test
    public void getByAgeCertificationNotFoundTest() {
        List<Media> result = mediaService.getByAgeCertification("PG-13");
        assertEquals(result, new ArrayList<>());
    }
    @Test
    public void getByProductionCountryTest() {
        List<Media> medias = List.of(media);
        when(mediaRepository.findByProductionCountries_NameIgnoreCase("US")).thenReturn(medias);

        List<Media> result = mediaService.getByProductionCountry("US");
        assertEquals(result, medias);
    }
    @Test
    public void getByProductionCountryNotFoundTest() {
        List<Media> result = mediaService.getByProductionCountry("UK");
        assertEquals(result, new ArrayList<>());

    }

    @Test
    public void getByTopImdbScoreTest() {
        List<Media> medias = List.of(media);
        when(mediaRepository.findTop10ByOrderByImdbScoreDesc()).thenReturn(medias);

        List<Media> result = mediaService.getByTopImdbScore();
        assertEquals(result, medias);
        //assertEquals(result.size(),10);
        // maybe something to check that the list is sorted by imdb score

    }
    @Test
    public void getByTopTmdbScoreTest() {
        List<Media> medias = List.of(media);
        when(mediaRepository.findTop10ByOrderByTmdbScoreDesc()).thenReturn(medias);

        List<Media> result = mediaService.getByTopTmdbScore();
        assertEquals(result, medias);
        //assertEquals(result.size(),10);
        // maybe something to check that the list is sorted by tmdb score

    }
    @Test
    public void getBySearchTermTest() {
        List<Media> medias = List.of(media);
        when(mediaRepository.findByTitleContainingIgnoreCase("Charlie")).thenReturn(medias);

        List<Media> result = mediaService.getBySearchTerm("Charlie");
        assertEquals(result, medias);
    }
    @Test
    public void getBySearchTermNotFoundTest() {
        List<Media> result = mediaService.getBySearchTerm("Boss Baby Back In Business");
        assertEquals(result, new ArrayList<>());
    }


}
