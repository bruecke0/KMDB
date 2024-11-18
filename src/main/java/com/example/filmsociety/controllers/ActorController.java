package com.example.filmsociety.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.filmsociety.entities.Actor;
import com.example.filmsociety.services.ActorService;



@RestController
public class ActorController {

    private ActorService actorService;

    @PostMapping(path = "/api/actors")
    public Actor createActor(@RequestBody Actor actor) {
        return actorService.createActor(actor);
    }

    @GetMapping(path = "/api/actors")
    public List<Actor> findAllActors(@RequestParam String param) {
        return actorService.findAllActors();
    }
    
    
}
