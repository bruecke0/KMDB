package com.example.filmsociety.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.filmsociety.entities.Actor;


@Repository
public interface ActorsRepository extends JpaRepository<Actor, Long> {
    
    List<Actor> findByName(String name);
}
