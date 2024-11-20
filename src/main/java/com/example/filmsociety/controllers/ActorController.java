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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.filmsociety.entities.Actor;
import com.example.filmsociety.services.ActorService;



@RestController
public class ActorController {

    private ActorService actorService;

    @PostMapping(path = "/api/actors")
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        return ResponseEntity.ok(actorService.createActor(actor));
    }

    @GetMapping(path = "/api/actors")
    public ResponseEntity<List<Actor>> findAllActors(@RequestParam(required=false) String name) {
        if (name != null) {
            return ResponseEntity.ok(actorService.getActorByName(name));
        }
        return ResponseEntity.ok(actorService.findAllActors());
    }

    @GetMapping(path = "/api/actors/{id}")
    public ResponseEntity<Optional<Actor>> findActorById(@PathVariable Long id) {
        return ResponseEntity.ok(actorService.findActorById(id));
    }

    @PatchMapping(path = "/api/actors/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable Long id, @RequestBody Actor updatedActor) {
        return ResponseEntity.ok(actorService.updateActor(id, updatedActor));
    }

    @DeleteMapping(path = "/api/actors/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
        return ResponseEntity.noContent().build();
    }
}
