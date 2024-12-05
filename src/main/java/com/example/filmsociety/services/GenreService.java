package com.example.filmsociety.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.filmsociety.entities.Genre;

public interface GenreService {
    Genre createGenre (Genre genre);
    Optional<Genre> findGenreById(Long id);
    Page<Genre> findAllGenres(int page, int size);
    Genre updateGenre(Long id, Genre updatedGenre);
    void deleteGenre(Long id, boolean force);
    

}
