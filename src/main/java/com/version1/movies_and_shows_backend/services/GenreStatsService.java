package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.dtos.GenreDTO;
import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.models.GenreStats;
import com.version1.movies_and_shows_backend.repositories.GenreRepository;
import com.version1.movies_and_shows_backend.repositories.GenreStatsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreStatsService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreStatsRepository genreStatsRepository;

    @Transactional
    public void generateAllStats() {
        List<GenreDTO> genres = genreRepository.getGenreStats();
        List<GenreStats> allStats = new ArrayList<>();

        for (GenreDTO genre:genres) {
            int genreId = genre.getId();
            Long mediaCount = genre.getMediaCount();
            Long castCount = genre.getCastCount();

            Optional<GenreStats> existingStatsOpt = genreStatsRepository.findById(genreId);

            GenreStats stats;
            if (existingStatsOpt.isPresent()) {
                // Update existing stats
                stats = existingStatsOpt.get();
                stats.setMediaCount(genre.getMediaCount());
                stats.setActorCount(castCount);
                stats.setUpdatedAt(LocalDateTime.now());
            } else {
                // Create new stats
                Genre g = genreRepository.findById(genreId).orElseThrow();
                stats = new GenreStats(g, mediaCount, castCount, LocalDateTime.now());
            }

            allStats.add(stats);
        }
        genreStatsRepository.saveAll(allStats);
    }
}

