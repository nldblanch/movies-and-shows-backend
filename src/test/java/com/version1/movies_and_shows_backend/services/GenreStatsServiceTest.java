package com.version1.movies_and_shows_backend.services;

import com.version1.movies_and_shows_backend.dtos.GenreDTO;
import com.version1.movies_and_shows_backend.helpers.CreateSamples;
import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.models.GenreStats;
import com.version1.movies_and_shows_backend.repositories.GenreRepository;
import com.version1.movies_and_shows_backend.repositories.GenreStatsRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GenreStatsServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private GenreStatsRepository genreStatsRepository;

    @InjectMocks
    private GenreStatsService genreStatsService;

    final List<Genre> genres = CreateSamples.genres();
    final List<GenreStats> genreStats = CreateSamples.genreStats();

    @Test
    public void generateAllStatsTest() {
        // Arrange
        List<GenreDTO> genreDTOs = List.of(
                new GenreDTO(1, "comedy", 10L, 20L),
                new GenreDTO(2, "drama", 15L, 30L), 
                new GenreDTO(3, "family", 8L, 16L) 
        );
        
        // Mock repository calls
        when(genreRepository.getGenreStats()).thenReturn(genreDTOs);
        
        // Mock existing stats for genres 1 and 2
        when(genreStatsRepository.findById(1)).thenReturn(Optional.of(genreStats.get(0)));
        when(genreStatsRepository.findById(2)).thenReturn(Optional.of(genreStats.get(1)));
        
        // Mock non-existing stats for genre 3
        when(genreStatsRepository.findById(3)).thenReturn(Optional.empty());
        when(genreRepository.findById(3)).thenReturn(Optional.of(genres.get(2)));
        
        // Act
        genreStatsService.generateAllStats();
        
        // Assert
        verify(genreRepository).getGenreStats();
        verify(genreStatsRepository).findById(1);
        verify(genreStatsRepository).findById(2);
        verify(genreStatsRepository).findById(3);
        verify(genreRepository).findById(3); // Only called for new stats
        verify(genreStatsRepository).saveAll(any());
        
        // Verify saveAll was called with correct number of stats
        verify(genreStatsRepository).saveAll(argThat(list -> ((List<?>) list).size() == 3));
    }
} 