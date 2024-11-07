package com.example.filmsociety.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.filmsociety.classes.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    
}
