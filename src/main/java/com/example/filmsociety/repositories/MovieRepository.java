package com.example.filmsociety.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.filmsociety.entities.Movies;

@Repository
public interface MovieRepository extends JpaRepository<Movies, Long> {

    Page<Movies> findByGenresId(Long genreId, Pageable pageable);
    Page<Movies> findByReleaseYear(Integer releaseYear, Pageable pageable);
    Page<Movies> findByActorsId(Long actorId, Pageable pageable);
    List<Movies> findByTitleContainingIgnoreCase(String title);
   
}
