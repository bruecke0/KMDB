package com.example.filmsociety.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;

    private String name;

    private LocalDate birthDate;

    @ManyToMany(mappedBy = "actors")
    private Set<Movies> movies = new HashSet<>();

    public Actor() {}

    public Actor(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    //getters and setters
    public Long getId() {
        return actorId;
    }

    public void setId(Long id) {
        this.actorId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Movies> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movies> movies) {
        this.movies = movies;
    }
}
