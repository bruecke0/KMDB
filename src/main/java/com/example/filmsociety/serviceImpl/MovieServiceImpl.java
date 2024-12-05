package com.example.filmsociety.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.filmsociety.entities.Actor;
import com.example.filmsociety.entities.Genre;
import com.example.filmsociety.entities.Movies;
import com.example.filmsociety.exceptions.ResourceNotFoundException;
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
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + genre.getId())));
        }
    movies.setGenres(genres);

    Set<Actor> actors = new HashSet<>();
    for (Actor actor : movies.getActors()) {
        actors.add(actorsRepository.findById(actor.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + actor.getId())));
        }
    movies.setActors(actors);
        return movieRepository.save(movies);
    }

    @Override
    public Optional<Movies> findMoviesById(Long id){
        return Optional.of(movieRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Movie with id " + id + " not found.")));
    }

    @Override
    public Page<Movies> findAllMovies(int page, int size){
        return movieRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Movies> findMoviesByGenreId(Long genreId, int page, int size){
        Page <Movies> movies = movieRepository.findByGenresId(genreId, PageRequest.of(page, size));
        if (movies.isEmpty()) {
            throw new ResourceNotFoundException("No movies found with genre id: " + genreId);
        }
        return movies;
    }

    @Override
    public Page<Movies> findMoviesByReleaseYear(Integer releaseYear, int page, int size){
        Page<Movies> movies = movieRepository.findByReleaseYear(releaseYear, PageRequest.of(page, size));
        if (movies.isEmpty()) {
            throw new ResourceNotFoundException("No movies found with the release year: " + releaseYear);
        }
        return movies;
    }

    @Override
    public Page<Movies> findMoviesByActorId (Long actorId, int page, int size){
        Page<Movies> movies = movieRepository.findByActorsId(actorId, PageRequest.of(page, size));
        if (movies.isEmpty()) {
            throw new ResourceNotFoundException("No movies found with actor id: " + actorId);
        }
        return movies;
    }

    @Override
    public List<Actor> findActorsByMovieId (Long movieId){
        Movies movies = movieRepository.findById(movieId)
        .orElseThrow(() -> new ResourceNotFoundException("Movie with id " + movieId + " not found."));
        return new ArrayList<>(movies.getActors());
    }

    @Override
    public List<Movies> findMoviesByMovieTitleContainingIgnoreCase (String title) {
        List<Movies> movies = movieRepository.findByTitleContainingIgnoreCase(title);
        if (movies.isEmpty()) {
            throw new ResourceNotFoundException("Movie not in the database");
        }
        return movies;
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
        .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
    }

    @Override
    public void deleteMovies(Long id){
        movieRepository.deleteById(id);
    }
}
