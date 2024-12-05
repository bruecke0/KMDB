package com.example.filmsociety.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.filmsociety.entities.Actor;
import com.example.filmsociety.entities.Movies;

public interface MovieService {
    Movies createMovies (Movies movies);
    Optional<Movies> findMoviesById(Long id);
    Page<Movies> findAllMovies(int page, int size);
    Page<Movies> findMoviesByGenreId(Long id, int page, int size);
    Page<Movies> findMoviesByReleaseYear(Integer releaseYear, int page, int size);
    Page<Movies> findMoviesByActorId(Long id, int page, int size);
    List<Actor> findActorsByMovieId(Long id);
    Movies updateMovies(Long id, Movies updatedMovies);
    void deleteMovies(Long id);
    List<Movies> findMoviesByMovieTitleContainingIgnoreCase(String title); 

}
