package com.example.filmsociety.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.filmsociety.entities.Actor;
import com.example.filmsociety.entities.Movies;
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
        return actorsRepository.findById(id);
    }

    @Override
    public List<Actor> getActorByName (String name){
        return actorsRepository.findByNameCaseInsensitive(name);
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
    public void deleteActor(Long id, boolean force){
        Actor actor = actorsRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Actor with id " + id + " not found."));
        if (actor.getMovies().isEmpty() || force) {
            if (force) {
                List<Movies> movies = movieRepository.findByActorsId(id);
                for (Movies movie : movies) {
                    movie.getActors().remove(actor);
                    movieRepository.save(movie);
                }
            }
            actorsRepository.deleteById(id);
        }else {
            throw new RuntimeException("Cannot delete actor '" + actor.getName() + "' because they are associated with " + actor.getMovies().size() +" movies.");
        }
    }
}
