package com.example.filmsociety.classes;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    @ManyToMany(mappedBy = "actors")
    private Set<Movies> movies = new HashSet<>();
}
