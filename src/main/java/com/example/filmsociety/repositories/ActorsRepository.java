package com.example.filmsociety.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.filmsociety.entities.Actor;


@Repository
public interface ActorsRepository extends JpaRepository<Actor, Long> {
    
    @Query("SELECT a FROM Actor a WHERE LOWER(a.name) = LOWER(:name)")
    List<Actor> findByNameCaseInsensitive(@Param("name") String name);
}
