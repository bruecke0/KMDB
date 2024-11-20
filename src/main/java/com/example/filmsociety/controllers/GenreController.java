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

import com.example.filmsociety.entities.Genre;
import com.example.filmsociety.services.GenreService;

public class GenreController {

    private GenreService genreService;

    @PostMapping (path = "/api/genres")
    public ResponseEntity <Genre> createGenre (@RequestBody Genre genre) {
        return ResponseEntity.ok(genreService.createGenre(genre));
    }

    @GetMapping (path = "/api/genres")
    public ResponseEntity <List <Genre>> findAllGenres () {
        return ResponseEntity.ok(genreService.findAllGenres());
    }

    @GetMapping (path = "/api/genres/{id}")
    public ResponseEntity <Optional<Genre>> findGenreById (@PathVariable Long id) {
        return ResponseEntity.ok(genreService.findGenreById(id));
    }

    @PatchMapping (path = "/api/genres/{id}")
    public ResponseEntity <Genre> updateGenre (@PathVariable Long id, @RequestBody Genre updatedGenre) {
        return ResponseEntity.ok(genreService.updateGenre(id, updatedGenre));
    }

    @DeleteMapping (path = "/api/genres/{id}")
    public ResponseEntity <Void> deleteGenre (@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }

}
