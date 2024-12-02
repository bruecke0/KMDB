package com.example.filmsociety.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.filmsociety.entities.Genre;
import com.example.filmsociety.entities.Movies;
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
        System.out.println("Saving Genre: " + genre.getName());
        return genreRepository.save(genre);
    }

    @Override
    public Optional<Genre> findGenreById(Long id){
        Optional<Genre> genre = genreRepository.findById(1L);
        System.out.println("Fetched Genre: " + genre.map(Genre::getName).orElse("Not found"));
        return genreRepository.findById(id);
    }

    @Override
    public List<Genre> findAllGenres(){
        /* return genreRepository.findAll(); */
        List<Genre> genres = genreRepository.findAll();
        for (Genre genre : genres) {
            if (genre == null) {
                System.out.println("Null genre object found.");
            }else{
            System.out.println("Genre ID: " + genre.getId() + ", Name: " + genre.getName());
        }
    }
        return genres;
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
