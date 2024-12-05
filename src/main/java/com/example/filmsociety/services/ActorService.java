package com.example.filmsociety.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.filmsociety.entities.Actor;

public interface ActorService {
    Actor createActor (Actor actor);
    Optional<Actor> findActorById(Long id);
    Page<Actor> findAllActors(int page, int size);
    Page<Actor> getActorByName (String name, int page, int size);
    Actor updateActor(Long id, Actor updatedActor);
    void deleteActor(Long id, boolean force);
}
