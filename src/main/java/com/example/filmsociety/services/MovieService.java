package com.example.filmsociety.services;

import java.util.List;
import java.util.Optional;

import com.example.filmsociety.entities.Movies;

public interface MovieService {
    Movies createMovies (Movies movies);
    Optional<Movies> findMoviesById(Long id);
    List<Movies> findAllMovies();
    Movies updateMovies(Long id, Movies updatedMovies);
    void deleteMovies(Long id);

}
