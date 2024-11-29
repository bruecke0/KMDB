package com.example.filmsociety.services;

import java.util.List;
import java.util.Optional;

import com.example.filmsociety.entities.Actor;
import com.example.filmsociety.entities.Movies;

public interface MovieService {
    Movies createMovies (Movies movies);
    Optional<Movies> findMoviesById(Long id);
    List<Movies> findAllMovies();
    List<Movies> findMoviesByGenreId(Long id);
    List<Movies> findMoviesByReleaseYear(Integer releaseYear);
    List<Movies> findMoviesByActorId(Long id);
    List<Actor> findActorsByMovieId(Long id);
    Movies updateMovies(Long id, Movies updatedMovies);
    void deleteMovies(Long id); 

}
