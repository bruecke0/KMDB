package com.example.filmsociety.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.filmsociety.entities.Movies;

@Repository
public interface MovieRepository extends JpaRepository<Movies, Long> {

    List<Movies> findByGenresId(Long genreId);
    List<Movies> findByReleaseYear(Integer releaseYear);
    List<Movies> findByActorId(Long actorId);
}
