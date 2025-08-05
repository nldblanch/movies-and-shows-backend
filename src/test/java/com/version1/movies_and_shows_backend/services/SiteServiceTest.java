package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.Site;
import com.version1.movies_and_shows_backend.repositories.CastRepository;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
import com.version1.movies_and_shows_backend.repositories.SiteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SiteServiceTest {
    @Mock
    private SiteRepository siteRepository;


    @InjectMocks
    private SiteService siteService;

    @Mock
    private MediaRepository mediaRepository;

    @InjectMocks
    private MediaService mediaService;

    final List<Site> sites = CreateSamples.sites();
    final Media media = CreateSamples.media().getFirst();

    @Test
    public void getAllSitesTest()
    {
        when(siteRepository.findAll()).thenReturn(sites);
        List<Site> result = siteService.getAllSites();

        assertEquals(sites, result);

    }

    @Test
    public void getAllSitesNotFoundTest()
    {

        List<Site> result = siteService.getAllSites();

        assertEquals(new ArrayList<>(), result);

    }
    @Test
    public void getSiteByNameTest()
    {
        Site site = sites.get(0);
        when(siteRepository.findByNameIgnoreCase("Apple")).thenReturn(Optional.of(site));
        Site result = siteService.getByName("Apple");
        assertEquals(result, site);

    }
    @Test
    public void getSiteByNameNotFoundTest()
    {
        Site result = siteService.getByName("Netflix");
        assertEquals(null, result);

        result = siteService.getByName("Amazon");
        assertEquals(null, result);
    }

    @Test
    public void getMediaBySiteTest()
    {
        List<Media> medias = List.of(media);
        when(mediaRepository.findBySites_NameIgnoreCase("Apple")).thenReturn(medias);
        List<Media> result = mediaService.getBySite("Apple");
        assertEquals(result, medias);


    }

    @Test
    public void getMediaBySiteNotFoundTest()
    {
        List<Media> result = mediaService.getBySite("Netflix");
        assertEquals(new ArrayList<Media>(), result);

        result = mediaService.getBySite("Amazon");
        assertEquals(new ArrayList<Media>(), result);
    }

}
