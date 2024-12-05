package com.example.filmsociety.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.filmsociety.entities.Actor;
import com.example.filmsociety.entities.Movies;
import com.example.filmsociety.exceptions.ResourceNotFoundException;
import com.example.filmsociety.repositories.ActorsRepository;
import com.example.filmsociety.repositories.MovieRepository;
import com.example.filmsociety.services.ActorService;

import jakarta.transaction.Transactional;

@Service
public class ActorServiceImpl implements ActorService {
    private final ActorsRepository actorsRepository;
    private final MovieRepository movieRepository;

    public ActorServiceImpl(ActorsRepository actorsRepository, MovieRepository movieRepository){
        this.actorsRepository = actorsRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    @Override
    public Actor createActor(Actor actor){
        return actorsRepository.save(actor);
    }

    @Override
    public Optional<Actor> findActorById(Long id){
        return Optional.ofNullable(actorsRepository.findById(id)
        .orElseThrow (() -> new ResourceNotFoundException("Actor with id " + id + " not found.")));
    }

    @Override
    public Page<Actor> getActorByName (String name, int page, int size){
        Page<Actor> actors = actorsRepository.findByNameCaseInsensitive(name, PageRequest.of(page, size));
        if  (actors.isEmpty()) {
            throw new ResourceNotFoundException("No actors found with name: " + name);
        }
        return actors;
    }

    @Override
    public Page<Actor> findAllActors(int page, int size){
        return actorsRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Actor updateActor(Long id, Actor updatedActor){
        return actorsRepository.findById(id)
        .map(actor -> {
            actor.setName(updatedActor.getName());
            actor.setBirthDate(updatedActor.getBirthDate());
            return actorsRepository.save(actor);
        })
        .orElseThrow(() -> new ResourceNotFoundException("Actor with id: " + id + " not found"));
    }

    @Override
    public void deleteActor(Long id, boolean force){
        Actor actor = actorsRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Actor with id " + id + " not found."));
        if (actor.getMovies().isEmpty() || force) {
            if (force) {
                List<Movies> movies = movieRepository.findByActorsId(id, Pageable.unpaged()).getContent();// for cases where you want all results without limiting the number of items
                for (Movies movie : movies) {
                    movie.getActors().remove(actor);
                    movieRepository.save(movie);
                }
            }
            actorsRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("Cannot delete actor '" + actor.getName() + "' because they are associated with " + actor.getMovies().size() +" movies.");
        }
    }
}
