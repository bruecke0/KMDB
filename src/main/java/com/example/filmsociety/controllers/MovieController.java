package com.example.filmsociety.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.filmsociety.entities.Actor;
import com.example.filmsociety.entities.Genre;
import com.example.filmsociety.entities.Movies;
import com.example.filmsociety.services.MovieService;

import jakarta.validation.Valid;



@RestController
@RequestMapping(path = "/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController (MovieService movieService){
        this.movieService = movieService;
    }

    @PostMapping 
    public ResponseEntity <Movies> createMovies (@Valid @RequestBody Movies movies) {
        Movies savedMovie = movieService.createMovies(movies);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @GetMapping 
    public ResponseEntity <Page <Movies>> findAllMovies (
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) Long genre,
        @RequestParam(required = false) Integer year, 
        @RequestParam(required = false) Long actor ) {
            final int maxPageSize = 200;
            size = Math.min(size, maxPageSize);

        if (genre != null){
            return ResponseEntity.ok(movieService.findMoviesByGenreId(genre, page, size));
        } else if (year != null) {
            return ResponseEntity.ok(movieService.findMoviesByReleaseYear(year, page, size));
        } else if (actor != null) {
            return ResponseEntity.ok(movieService.findMoviesByActorId(actor, page, size));
        }
        
        return ResponseEntity.ok(movieService.findAllMovies(page, size));
    }

    @GetMapping(path = "/{id}/actors")
    public ResponseEntity <List<Actor>> findActorsByMovieId (@PathVariable Long id) {
        return ResponseEntity.ok(movieService.findActorsByMovieId(id));
    }
    

    @GetMapping (path = "/{id}") 
    public ResponseEntity <Optional <Movies>> findMoviesById (@PathVariable Long id) {
        Optional<Movies> movies = movieService.findMoviesById(id);
        return ResponseEntity.ok(movies);
    }

    @GetMapping(path = "/search")
    public ResponseEntity <List<Movies>> findMoviesByMovieTitleContainingIgnoreCase(@RequestParam String title) {
        List<Movies> movies = movieService.findMoviesByMovieTitleContainingIgnoreCase(title);
        return ResponseEntity.ok(movies);
    }
    

    @PatchMapping (path = "/{id}")
    public ResponseEntity <Movies> updateMovies (@Valid @PathVariable Long id, @RequestBody Movies updatedMovies) {
        return ResponseEntity.ok(movieService.updateMovies(id, updatedMovies));
    }

    @DeleteMapping (path = "/{id}")
    public ResponseEntity <Void> deleteMovies (@PathVariable Long id) {
        movieService.deleteMovies(id);
        return ResponseEntity.noContent().build();
    }

    
}
