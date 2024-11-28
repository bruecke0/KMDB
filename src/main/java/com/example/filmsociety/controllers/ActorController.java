package com.example.filmsociety.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.filmsociety.entities.Actor;
import com.example.filmsociety.services.ActorService;



@RestController
@RequestMapping(path = "/api/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService){
        this.actorService = actorService;
    }

    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        return ResponseEntity.ok(actorService.createActor(actor));
    }

    @GetMapping
    public ResponseEntity<List<Actor>> findAllActors(@RequestParam(required=false) String name) {
        if (name != null) {
            return ResponseEntity.ok(actorService.getActorByName(name));
        }
        return ResponseEntity.ok(actorService.findAllActors());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Actor>> findActorById(@PathVariable Long id) {
        return ResponseEntity.ok(actorService.findActorById(id));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable Long id, @RequestBody Actor updatedActor) {
        return ResponseEntity.ok(actorService.updateActor(id, updatedActor));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id, @RequestParam(required = false, defaultValue = "false") boolean force) {
        actorService.deleteActor(id, force);
        return ResponseEntity.noContent().build();
    }
}
