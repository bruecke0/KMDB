package com.example.filmsociety.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    @NotNull
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Movies> movies = new HashSet<>();


    //getters and setters
    public Genre() {}

    public Genre(String name) {
        this.name = name;
    }

    public Long getId() {
        return genreId;
    }

    public void setId(Long id) {
        this.genreId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movies> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movies> movies) {
        this.movies = movies;
    }
}
