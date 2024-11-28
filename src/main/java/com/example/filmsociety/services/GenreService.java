package com.example.filmsociety.services;

import java.util.List;
import java.util.Optional;

import com.example.filmsociety.entities.Genre;

public interface GenreService {
    Genre createGenre (Genre genre);
    Optional<Genre> findGenreById(Long id);
    List<Genre> findAllGenres();
    Genre updateGenre(Long id, Genre updatedGenre);
    void deleteGenre(Long id, boolean force);
    

}
