package com.example.filmsociety.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.filmsociety.classes.Movies;
import com.example.filmsociety.repositories.MovieRepository;
import com.example.filmsociety.services.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @Override
    public Movies createMovies(Movies movies){
        return movieRepository.save(movies);
    }

    @Override
    public Optional<Movies> findMoviesById(Long id){
        return movieRepository.findById(id);
    }

    @Override
    public List<Movies> findAllMovies(){
        return movieRepository.findAll();
    }

    @Override
    public Movies updateMovies(Long id, Movies updatedMovies){
        return movieRepository.findById(id)
        .map((movies) -> {
            movies.setName(updatedMovies.getName());
            movies.setReleaseYear(updatedMovies.getReleaseYear());
            movies.setDuration(updatedMovies.getDuration());
            return movieRepository.save(movies);
        })
        .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @Override
    public void deleteMovies(Long id){
        movieRepository.deleteById(id);
    }
}
