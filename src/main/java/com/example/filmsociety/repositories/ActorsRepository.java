package com.example.filmsociety.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.filmsociety.entities.Actor;


@Repository
public interface ActorsRepository extends JpaRepository<Actor, Long> {
    
    @Query("SELECT a FROM Actor a WHERE LOWER(a.name) LIKE (CONCAT('%', :name, '%'))") //allows partial matching, making it more flexible for name searches.
    Page<Actor> findByNameCaseInsensitive(@Param("name") String name, Pageable pageable);
}
