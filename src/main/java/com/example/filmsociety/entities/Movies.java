package com.example.filmsociety.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "movies")
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private Integer releaseYear;

    @NotNull
    private Integer duration;

    @ManyToMany(fetch=FetchType.EAGER) //manytomany between movies and genres
    @JoinTable(
        name = "movie_genre",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @NotEmpty
    private Set<Genre> genres;
    
    @ManyToMany(fetch=FetchType.EAGER) //manytomany between movies and actors
    @JoinTable(
        name = "movie_actor",
        joinColumns = @JoinColumn(name = "movie_id"), 
        inverseJoinColumns = @JoinColumn(name = "actor_id")
        )
        
    @JsonIgnoreProperties("movies")
    private Set<Actor> actors = new HashSet<>();

    public Movies() {}

    public Movies(String title, Integer releaseYear, Integer duration, Set<Genre> genres, Set<Actor> actors) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.genres = genres;
        this.actors = actors;
    }


    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Set<Genre> getGenres () {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

}
