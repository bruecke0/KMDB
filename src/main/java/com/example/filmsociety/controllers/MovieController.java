package com.example.filmsociety.controllers;

import java.util.List;
import java.util.Optional;

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

import com.example.filmsociety.entities.Movies;
import com.example.filmsociety.services.MovieService;



@RestController
@RequestMapping(path = "/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController (MovieService movieService){
        this.movieService = movieService;
    }

    @PostMapping 
    public ResponseEntity <Movies> createMovies (@RequestBody Movies movies) {
        return ResponseEntity.ok(movieService.createMovies(movies));
    }

    @GetMapping 
    public ResponseEntity <List <Movies>> findAllMovies (
        @RequestParam(required = false) Long genre,
        @RequestParam(required = false) Integer year, 
        @RequestParam(required = false) Long actor ) {

        if (genre != null){
            return ResponseEntity.ok(movieService.findMoviesByGenreId(genre));
        } else if (year != null) {
            return ResponseEntity.ok(movieService.findMoviesByReleaseYear(year));
        } else if (actor != null) {
            return ResponseEntity.ok(movieService.findMoviesByActorId(actor));
        }
        
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @GetMapping (path = "/{id}") 
    public ResponseEntity <Optional <Movies>> findMoviesById (@PathVariable Long id) {
        return ResponseEntity.ok(movieService.findMoviesById(id));
    }

    @PatchMapping (path = "/{id}")
    public ResponseEntity <Movies> updateMovies (@PathVariable Long id, @RequestBody Movies updatedMovies) {
        return ResponseEntity.ok(movieService.updateMovies(id, updatedMovies));
    }

    @DeleteMapping (path = "/{id}")
    public ResponseEntity <Void> deleteMovies (@PathVariable Long id) {
        movieService.deleteMovies(id);
        return ResponseEntity.noContent().build();
    }

    /* actors by movie id endpoint*/
}
