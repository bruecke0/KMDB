package com.example.filmsociety.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.filmsociety.entities.Actor;
import com.example.filmsociety.entities.Genre;
import com.example.filmsociety.entities.Movies;
import com.example.filmsociety.repositories.ActorsRepository;
import com.example.filmsociety.repositories.GenreRepository;
import com.example.filmsociety.repositories.MovieRepository;
import com.example.filmsociety.services.MovieService;

import jakarta.transaction.Transactional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final ActorsRepository actorsRepository;
    

    public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository, ActorsRepository actorsRepository){
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.actorsRepository = actorsRepository;
    }


    @Transactional
    @Override
    public Movies createMovies(Movies movies){
        Set<Genre> genres = new HashSet<>();
    for (Genre genre : movies.getGenres()) {
        genres.add(genreRepository.findById(genre.getId())
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + genre.getId())));
    }
    movies.setGenres(genres);

    Set<Actor> actors = new HashSet<>();
    for (Actor actor : movies.getActors()) {
        actors.add(actorsRepository.findById(actor.getId())
                .orElseThrow(() -> new RuntimeException("Actor not found with id: " + actor.getId())));
    }
    movies.setActors(actors);
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
    public List<Movies> findMoviesByGenreId(Long genreId){
        if (!genreRepository.existsById(genreId)){
            throw new RuntimeException("Genre with id " + genreId + " not found."); //we'll see
        }
       List<Movies> movies = movieRepository.findByGenresId(genreId);
        return movies.isEmpty() ? Collections.emptyList() : movies;  //if there are no movies in this genre, return empty list
    }

    @Override
    public List<Movies> findMoviesByReleaseYear(Integer releaseYear){
        List<Movies> movies = movieRepository.findByReleaseYear(releaseYear);
        return movies.isEmpty() ? Collections.emptyList() : movies;
    }

    @Override
    public List<Movies> findMoviesByActorId (Long actorId){
        List<Movies> movies = movieRepository.findByActorsId(actorId);
        return movies.isEmpty() ? Collections.emptyList() : movies;
    }

    @Override
    public List<Actor> findActorsByMovieId (Long movieId){
        Movies movie = movieRepository.findById(movieId)
        .orElseThrow(() -> new RuntimeException("Movie with id " + movieId + " not found."));
        return new ArrayList<>(movie.getActors());
    }

    @Override
    public Movies updateMovies(Long id, Movies updatedMovies){
        return movieRepository.findById(id)
        .map((movies) -> {
            movies.setTitle(updatedMovies.getTitle());
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
