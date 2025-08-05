package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.models.Cast;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.repositories.CastRepository;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    CastRepository castRepository;

    public Media getMediaById(String id) {
        return mediaRepository.findById(id).orElse(null);
    }

    public List<Media> getAllMedia() { return mediaRepository.findAll();}

    // move to genre
    public List<Media> getByGenre(String genre) { return mediaRepository.findByGenres_NameIgnoreCase(genre);}

    // move to site
    public List<Media> getBySite(String site) { return mediaRepository.findBySites_NameIgnoreCase(site);}


    public Media getByTitle(String title) { return mediaRepository.findFirstByTitleIgnoreCase(title).orElse(null);}


    // cast by media
    // idk if should use media or media id, media seems easier with jpa
    public List<Cast> getCastByMedia(Media media) { return  castRepository.findByMedia(media);}


    // get by year
    public List<Media> getByYear(int year) {return mediaRepository.findByReleaseYear(year);}


    // get by type
    public List<Media> getByType(String type) { return mediaRepository.findByTypeIgnoreCase(type);}

    // get by age Certification
    public List<Media> getByAgeCertification(String ageCertification) {
        return mediaRepository.findByAgeCertIgnoreCase(ageCertification);
    }



    // get by production country
    public List<Media> getByProductionCountry(String productionCountry) {
        return mediaRepository.findByProductionCountries_NameIgnoreCase(productionCountry);
    }

    // get by top imdb score
    public List<Media> getByTopImdbScore() {
        return mediaRepository.findTop10ByOrderByImdbScoreDesc();
    }
    // get by top tmdb score
    public List<Media> getByTopTmdbScore() {
        return mediaRepository.findTop10ByOrderByTmdbScoreDesc();
    }
    // get by search term
    public List<Media> getBySearchTerm(String searchTerm) {
        return mediaRepository.findByTitleContainingIgnoreCase(searchTerm);
    }




}
