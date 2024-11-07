package com.example.filmsociety.services;

import java.util.List;
import java.util.Optional;

import com.example.filmsociety.classes.Actor;

public interface ActorService {
    Actor createActor (Actor actor);
    Optional<Actor> findActorById(Long id);
    List<Actor> findAllActors();
    Actor updateActor(Long id, Actor updatedActor);
    void deleteActor(Long id);
}
