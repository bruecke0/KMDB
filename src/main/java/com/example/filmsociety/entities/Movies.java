package com.example.filmsociety.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "movies")
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String title;

    private int releaseYear;

    private int duration;

    @ManyToMany(fetch=FetchType.EAGER) //manytomany between movies and genres
    @JoinTable(
        name = "movie_genre",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @ManyToMany(fetch=FetchType.EAGER) //manytomany between movies and actors
    @JoinTable(
        name = "movie_actor",
        joinColumns = @JoinColumn(name = "movie_id"), 
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )

    private Set<Actor> actors = new HashSet<>();

    public Movies() {}

    public Movies(String title, int releaseYear, int duration) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
    }


    //getters and setters
    public Long getId() {
        return movieId;
    }

    public void setId(Long id) {
        this.movieId = id;
    }

    public String getName() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
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


}
