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

import com.example.filmsociety.entities.Movies;
import com.example.filmsociety.services.MovieService;

public class MovieController {

    private MovieService movieService;

    @PostMapping (path = "/api/movies")
    public ResponseEntity <Movies> createMovies (@RequestBody Movies movies) {
        return ResponseEntity.ok(movieService.createMovies(movies));
    }

    @GetMapping (path = "/api/movies")
    public ResponseEntity <List <Movies>> findAllMovies () {
        /* siia veel asju lisada */
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @GetMapping (path = "/api/movies/{id}") 
    public ResponseEntity <Optional <Movies>> findMoviesById (@PathVariable Long id) {
        return ResponseEntity.ok(movieService.findMoviesById(id));
    }

    @PatchMapping (path = "/api/movies/{id}")
    public ResponseEntity <Movies> updateMovies (@PathVariable Long id, @RequestBody Movies updatedMovies) {
        return ResponseEntity.ok(movieService.updateMovies(id, updatedMovies));
    }

    @DeleteMapping (path = "/api/movies/{id}")
    public ResponseEntity <Void> deleteMovies (@PathVariable Long id) {
        movieService.deleteMovies(id);
        return ResponseEntity.noContent().build();
    }

    /* actors by movie id endpoint*/
}
