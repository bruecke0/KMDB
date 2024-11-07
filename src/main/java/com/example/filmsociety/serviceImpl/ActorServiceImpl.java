package com.example.filmsociety.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.filmsociety.classes.Actor;
import com.example.filmsociety.repositories.ActorsRepository;
import com.example.filmsociety.services.ActorService;

@Service
public class ActorServiceImpl implements ActorService {
    private final ActorsRepository actorsRepository;

    public ActorServiceImpl(ActorsRepository actorsRepository){
        this.actorsRepository = actorsRepository;
    }

    @Override
    public Actor createActor(Actor actor){
        return actorsRepository.save(actor);
    }

    @Override
    public Optional<Actor> findActorById(Long id){
        return actorsRepository.findById(id);
    }

    @Override
    public List<Actor> findAllActors(){
        return actorsRepository.findAll();
    }

    @Override
    public Actor updateActor(Long id, Actor updatedActor){
        return actorsRepository.findById(id)
        .map(actor -> {
            actor.setName(updatedActor.getName());
            return actorsRepository.save(actor);
        })
        .orElseThrow(() -> new RuntimeException("Actor not found"));
    }

    @Override
    public void deleteActor(Long id){
        actorsRepository.deleteById(id);
    }
}
