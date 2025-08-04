package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.models.Genre;
import com.version1.movies_and_shows_backend.models.GenreStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class GenreStatsRepositoryTest {
    private final List<Genre> genres = List.of(new Genre("comedy"), new Genre("drama"), new Genre("family"), new Genre("music"), new Genre("animation"), new Genre("scifi"), new Genre("action"), new Genre("fantasy"));

    private final List<GenreStats> stats = new ArrayList<>();
    private final Random random = new Random();
    @Autowired
    private GenreStatsRepository genreStatsRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void setup() {
        for (Genre genre : genres) {
            testEntityManager.persist(genre);
        }
        testEntityManager.flush();

        List<Genre> savedGenres = genreRepository.findAll();
        for (Genre genre : savedGenres) {
            long mediaCount = random.nextInt(100);
            long actorCount = random.nextInt(100) * 100;
            stats.add(new GenreStats(genre, mediaCount, actorCount, LocalDate.of(2024, 12, 31).atStartOfDay()));
        }

        for (GenreStats stat : stats) {
            testEntityManager.persist(stat);
        }
        testEntityManager.flush();
    }

    @Test
    public void findAllTest() {
        List<GenreStats> stats = genreStatsRepository.findAll();
        assertEquals(8, stats.size());
    }

    @Test
    public void findByIdTest() {
        // Get the first stats entry from setup
        GenreStats firstStats = genreStatsRepository.findAll().getFirst();

        // Find by Genre ID (one to one)
        Optional<GenreStats> foundStats = genreStatsRepository.findById(firstStats.getId());

        assertTrue(foundStats.isPresent());
        assertEquals(firstStats.getId(), foundStats.get().getId());
        assertEquals(firstStats.getMediaCount(), foundStats.get().getMediaCount());
        assertEquals(firstStats.getActorCount(), foundStats.get().getActorCount());
        assertEquals(firstStats.getGenre().getName(), foundStats.get().getGenre().getName());
    }

    @Test
    public void findByIdNotFoundTest() {
        GenreStats foundStats = genreStatsRepository.findById(999).orElse(null);
        assertNull(foundStats);
    }

    @Test
    public void saveTest() {
        GenreStats firstStats = genreStatsRepository.findAll().getFirst();
        GenreStats stats = genreStatsRepository.save(firstStats);
        assertNotNull(stats);
        assertEquals(firstStats, stats);
    }

    @Test
    public void deleteTest() {
        GenreStats firstStats = genreStatsRepository.findAll().getFirst();
        GenreStats stats = genreStatsRepository.findById(firstStats.getId()).orElse(null);
        assertNotNull(stats);
        genreStatsRepository.delete(stats);
        GenreStats deletedStats = genreStatsRepository.findById(firstStats.getId()).orElse(null);
        assertNull(deletedStats);
    }
} 