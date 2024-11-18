package com.example.filmsociety.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.filmsociety.entities.Movies;

@Repository
public interface MovieRepository extends JpaRepository<Movies, Long> {
    
}
