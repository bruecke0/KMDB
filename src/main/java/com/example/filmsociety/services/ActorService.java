package com.example.filmsociety.services;

import java.util.List;
import java.util.Optional;

import com.example.filmsociety.entities.Actor;

public interface ActorService {
    Actor createActor (Actor actor);
    Optional<Actor> findActorById(Long id);
    List<Actor> findAllActors();
    List<Actor> getActorByName (String name);
    Actor updateActor(Long id, Actor updatedActor);
    void deleteActor(Long id, boolean force);
}
