package com.version1.movies_and_shows_backend.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "genre_stats")
public class GenreStats implements Serializable {

    @Id
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name="genre_id")
    private Genre genre;

    @Column(name="media_count", nullable=false)
    private Long mediaCount;

    @Column(name="actor_count", nullable=false)
    private Long actorCount;

    @Column(name="updated_at", nullable=false)
    private LocalDateTime updatedAt;


    public GenreStats() {}

    public GenreStats(Genre genre, Long mediaCount, Long actorCount, LocalDateTime updatedAt) {
        this.mediaCount = mediaCount;
        this.actorCount = actorCount;
        this.updatedAt = updatedAt;
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMediaCount() {
        return mediaCount;
    }

    public void setMediaCount(Long mediaCount) {
        this.mediaCount = mediaCount;
    }

    public Long getActorCount() {
        return actorCount;
    }

    public void setActorCount(Long actorCount) {
        this.actorCount = actorCount;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


}
