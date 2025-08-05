package com.version1.movies_and_shows_backend.dtos;

import com.version1.movies_and_shows_backend.models.Cast;
import com.version1.movies_and_shows_backend.models.Media;
import com.version1.movies_and_shows_backend.models.ProductionCountry;
import com.version1.movies_and_shows_backend.models.Site;

import java.util.List;
import java.util.stream.Collectors;

public class MediaDTO {
    private String id;
    private String title;
    private List<GenreDTO> genres;
    private String type;
    private String description;
    private int releaseYear;
    private String ageCert;
    private int runtime;
    private double seasons;
    private String imdbId;
    private double imdbScore;
    private double imdbVotes;
    private double tmdbPopularity;
    private double tmdbScore;

    // to change when added DTOs
    private List<ProductionCountry> productionCountries;
    private List<Site> sites;
    private List<Cast> cast;



    public MediaDTO() {
    }

    public MediaDTO(String title, List<GenreDTO> genreDTOs) {
        this.title = title;
        this.genres = genreDTOs;
    }
    // add more when DTOs are done
    public MediaDTO(Media media) {
        this.id = media.getId();
        this.title = media.getTitle();
        this.genres = media.getGenres().stream()
                .map(genre -> new GenreDTO(genre.getId(), genre.getName()))
                .toList();
        this.ageCert = media.getAgeCert();
        this.description = media.getDescription();
        this.releaseYear = media.getReleaseYear();
        this.runtime = media.getRuntime();
        this.seasons = media.getSeasons();
        this.imdbId = media.getImdbId();
        this.imdbScore = media.getImdbScore();
        this.imdbVotes = media.getImdbVotes();
        this.tmdbPopularity = media.getTmdbPopularity();
        this.tmdbScore = media.getTmdbScore();
        this.type = media.getType();

        this.productionCountries = media.getProductionCountries().stream().toList();
        this.sites = media.getSites().stream().toList();
        this.cast = media.getCast().stream()
                .map(cast -> new Cast(
                        cast.getMedia(),
                        cast.getPerson(),
                        cast.getCharacter(),
                        cast.getRole()
                ))
                .collect(Collectors.toList());





    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getAgeCert() {
        return ageCert;
    }

    public void setAgeCert(String ageCert) {
        this.ageCert = ageCert;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public double getSeasons() {
        return seasons;
    }

    public void setSeasons(double seasons) {
        this.seasons = seasons;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public double getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(double imdbScore) {
        this.imdbScore = imdbScore;
    }

    public double getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(double imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public double getTmdbPopularity() {
        return tmdbPopularity;
    }

    public void setTmdbPopularity(double tmdbPopularity) {
        this.tmdbPopularity = tmdbPopularity;
    }

    public double getTmdbScore() {
        return tmdbScore;
    }

    public void setTmdbScore(double tmdbScore) {
        this.tmdbScore = tmdbScore;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }
}
