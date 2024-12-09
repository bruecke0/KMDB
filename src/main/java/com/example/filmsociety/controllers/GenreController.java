package com.example.filmsociety.controllers;

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

import com.example.filmsociety.entities.Genre;
import com.example.filmsociety.services.GenreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping 
    public ResponseEntity <Genre> createGenre (@Valid @RequestBody Genre genre) {
        Genre savedGenre = genreService.createGenre(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    @GetMapping 
    public ResponseEntity <Page <Genre>> findAllGenres (
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size ) {
            final int maxPageSize = 200;
            size = Math.min(size, maxPageSize);
        return ResponseEntity.ok(genreService.findAllGenres(page, size));
    }

    @GetMapping (path = "/{id}")
    public ResponseEntity <Optional<Genre>> findGenreById (@PathVariable Long id) {
        return ResponseEntity.ok(genreService.findGenreById(id));
    }

    @PatchMapping (path = "/{id}")
    public ResponseEntity <Genre> updateGenre (@Valid @PathVariable Long id, @RequestBody Genre updatedGenre) {
        return ResponseEntity.ok(genreService.updateGenre(id, updatedGenre));
    }

    @DeleteMapping (path = "/{id}")
    public ResponseEntity <Void> deleteGenre (@PathVariable Long id, @RequestParam(required = false, defaultValue = "false")boolean force) {
        genreService.deleteGenre(id, force);
        return ResponseEntity.noContent().build();
    }

}
