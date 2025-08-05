package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.dtos.GenreDTO;
import com.version1.movies_and_shows_backend.dtos.MediaDTO;
import com.version1.movies_and_shows_backend.exceptions.GenreNotFoundException;
import com.version1.movies_and_shows_backend.mappers.GenreMapper;
import com.version1.movies_and_shows_backend.mappers.MediaMapper;
import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.models.GenreStats;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.repositories.GenreRepository;
import com.version1.movies_and_shows_backend.repositories.GenreStatsRepository;
import com.version1.movies_and_shows_backend.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreStatsRepository genreStatsRepository;

    @Autowired
    private MediaRepository mediaRepository;

    public List<GenreDTO> getAllGenres() {
        List<GenreStats> genreStats = genreStatsRepository.findAll();

        Map<Integer, GenreStats> statsByGenreId = genreStats.stream()
                .collect(Collectors.toMap(stats -> stats.getGenre().getId(), Function.identity()));


        return genreRepository.findAll().stream().map(genre -> {
            GenreDTO dto = GenreMapper.toDTO(genre);
            GenreStats stats = statsByGenreId.get(genre.getId());
            if (stats != null) {
                dto.setMediaCount(stats.getMediaCount());
                dto.setCastCount(stats.getActorCount());
            }
            return dto;
        }).toList();

    }

    public GenreDTO getGenreByName(String name) {
        Genre genre = genreRepository.findByNameIgnoreCase(name).orElse(null);
        if (genre == null) {
            return null;
        }
        else {
            return new GenreDTO(genre.getId(), genre.getName());
        }
    }

    public List<MediaDTO> getMediaByGenre(String name) {
        List<Media> mediaList = mediaRepository.findByGenres_NameIgnoreCase(name);
        return mediaList.stream().map(MediaDTO::new).toList();
    }


    public List<MediaDTO> getTop10MoviesByGenre(String name) {
        List<Media> mediaList = mediaRepository.findTop10ByGenres_NameIgnoreCaseAndTypeOrderByImdbScoreDesc(name, "movie");
        return mediaList.stream().map(MediaDTO::new).toList();
    }
    public List<MediaDTO> getTop10ShowsByGenre(String name) {
        List<Media> mediaList = mediaRepository.findTop10ByGenres_NameIgnoreCaseAndTypeOrderByImdbScoreDesc(name, "show");
        return mediaList.stream().map(MediaDTO::new).toList();
    }
}
