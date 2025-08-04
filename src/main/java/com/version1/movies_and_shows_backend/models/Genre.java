package com.version1.movies_and_shows_backend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToOne(mappedBy = "genre", fetch = FetchType.LAZY)
    private GenreStats stats;

    public Genre() {}

    public Genre(String name) {
        this.name = name;
    }

    public Genre(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("{id: %d, name: %s }", getId(), getName());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Genre genre)) return false;
        return name.equalsIgnoreCase(genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
