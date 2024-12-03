package com.example.filmsociety.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.filmsociety.entities.Genre;
import com.example.filmsociety.entities.Movies;
import com.example.filmsociety.exceptions.ResourceNotFoundException;
import com.example.filmsociety.repositories.GenreRepository;
import com.example.filmsociety.repositories.MovieRepository;
import com.example.filmsociety.services.GenreService;

import jakarta.transaction.Transactional;


@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;


    
    public GenreServiceImpl(GenreRepository genreRepository, MovieRepository movieRepository){
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    @Override
    public Genre createGenre(Genre genre){
        return genreRepository.save(genre);
    }

    @Override
    public Optional<Genre> findGenreById(Long id){
        return Optional.ofNullable(genreRepository.findById(id)
        .orElseThrow (() -> new ResourceNotFoundException("Genre with id " + id + " not found.")));
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
        .orElseThrow(() -> new ResourceNotFoundException("Genre with id: " + id + " not found"));
    }

    @Override
    @Transactional
    public void deleteGenre(Long id, boolean force){
        Genre genre = genreRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Genre with id " + id + " not found"));
        if (genre.getMovies().isEmpty() || force) {
            List<Movies> movies = movieRepository.findByGenresId(id);
            for (Movies movie : movies) {
                movie.getGenres().remove(genre);
            }
            movieRepository.saveAll(movies);
            genreRepository.deleteById(id);
        }else{
            throw new RuntimeException("Cannot delete genre '" + genre.getName() + "' because it has " + genre.getMovies().size() + " associated movies.");
        }
    }
}
