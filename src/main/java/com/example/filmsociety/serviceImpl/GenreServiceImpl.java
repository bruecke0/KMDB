package com.example.filmsociety.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.filmsociety.classes.Genre;
import com.example.filmsociety.repositories.GenreRepository;
import com.example.filmsociety.services.GenreService;


@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    
    public GenreServiceImpl(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre createGenre(Genre genre){
        return genreRepository.save(genre);
    }

    @Override
    public Optional<Genre> findGenreById(Long id){
        return genreRepository.findById(id);
    }

    @Override
    public List<Genre> findAllGenres(){
        return genreRepository.findAll();
    }

    @Override
    public Genre updateGenre(Long id, Genre updatedGenre){
        return genreRepository.findById(id)
        .map(genre -> {
            genre.setName(updatedGenre.getName());
            return genreRepository.save(genre);
        })
        .orElseThrow(() -> new RuntimeException("Genre not found"));
    }
    @Override
    public void deleteGenre(Long id){
        genreRepository.deleteById(id);
    }

}
