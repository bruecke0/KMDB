package com.example.filmsociety.controllers;

import java.util.Optional;

import org.springframework.data.domain.Page;
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

import jakarta.validation.Valid;



@RestController
@RequestMapping(path = "/api/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService){
        this.actorService = actorService;
    }

    @PostMapping
    public ResponseEntity<Actor> createActor(@Valid @RequestBody Actor actor) {
        return ResponseEntity.ok(actorService.createActor(actor));
    }

    @GetMapping
    public ResponseEntity<Page<Actor>> findAllActors(
        @RequestParam(required=false) String name,
        @RequestParam(defaultValue = "0") int page, 
        @RequestParam(defaultValue = "10") int size ) {
            final int maxPageSize = 200;
            size = Math.min(size, maxPageSize);
        if (name != null) {
            return ResponseEntity.ok(actorService.getActorByName(name, page, size));
        }
        return ResponseEntity.ok(actorService.findAllActors(page, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Actor>> findActorById(@PathVariable Long id) {
        return ResponseEntity.ok(actorService.findActorById(id));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Actor> updateActor(@Valid @PathVariable Long id, @RequestBody Actor updatedActor) {
        return ResponseEntity.ok(actorService.updateActor(id, updatedActor));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id, @RequestParam(required = false, defaultValue = "false") boolean force) {
        actorService.deleteActor(id, force);
        return ResponseEntity.noContent().build();
    }
}
