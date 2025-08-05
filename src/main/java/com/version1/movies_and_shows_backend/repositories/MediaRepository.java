package com.version1.movies_and_shows_backend.repositories;

import com.version1.movies_and_shows_backend.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, String> {
    List<Media> findByGenres_NameIgnoreCase(String name);

    List<Media> findBySites_NameIgnoreCase(String name);

    Optional<Media> findFirstByTitleIgnoreCase(String title);

    List<Media> findByReleaseYear(int year);

    List<Media> findByTypeIgnoreCase(String type);

    List<Media> findByAgeCertIgnoreCase(String ageCertification);

    List<Media> findByProductionCountries_NameIgnoreCase(String productionCountry);

    List<Media> findTop10ByOrderByImdbScoreDesc();

    List<Media> findTop10ByOrderByTmdbScoreDesc();

    List<Media> findByTitleContainingIgnoreCase(String title);

    List<Media> findTop10ByGenres_NameIgnoreCaseAndTypeOrderByImdbScoreDesc(String genreName, String type);

    List<Media> findTop10BySites_NameIgnoreCaseAndTypeOrderByImdbScoreDesc(String siteName, String type);

}
