package com.version1.movies_and_shows_backend.controllers;

import com.version1.movies_and_shows_backend.dtos.GenreDTO;
import com.version1.movies_and_shows_backend.models.Cast;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
import com.version1.movies_and_shows_backend.services.MediaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @Transactional
    @GetMapping("/{id}")
    public Media getMedia(@PathVariable String id) {

        Media media = mediaService.getMediaById(id);
        if (media != null) {
            // Return media details or process as needed
            System.out.println("Media found: " + media.getTitle());
        } else {
            // Handle case where media is not found
            System.out.println("Media not found for ID: " + id);
        }
        return media;

    }

    @Transactional
    @GetMapping("/titles")
    public List<Media> getAllMedia() {
        return mediaService.getAllMedia();
    }

    @Transactional
    @GetMapping("/{id}/cast")
    public List<Cast> getCastByMedia(@PathVariable String id) {
        Media media = mediaService.getMediaById(id);
        if (media != null) {
            return mediaService.getCastByMedia(media);
        } else {
            System.out.println("Media not found for ID: " + id);
            return List.of(); // Return an empty list if media not found
        }
    }

    @Transactional
    @GetMapping("/{year}")
    public List<Media> getByYear(@PathVariable int year) {
        return mediaService.getByYear(year);
    }
    @Transactional
    @GetMapping("/{type}")
    public List<Media> getByType(@PathVariable String type) {
        return mediaService.getByType(type);

    }
    @Transactional
    @GetMapping("/{ageCertification}")
    public List<Media> getByAgeCertification(@PathVariable String ageCertification) {
        return mediaService.getByAgeCertification(ageCertification);

    }

    @Transactional
    @GetMapping("/{productionCountry}")
    public List<Media> getByProductionCountry(@PathVariable String productionCountry) {
        return mediaService.getByProductionCountry(productionCountry);

    }
    @Transactional
    @GetMapping("/top-imdb")
    public List<Media> getByTopImdbScore() {
        return mediaService.getByTopImdbScore();
    }
    @Transactional
    @GetMapping("/top-tmdb")
    public List<Media> getByTopTmdbScore() {
        return mediaService.getByTopTmdbScore();

    }


}
