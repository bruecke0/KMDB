# MOVIES API
A robust API for managing a movie database using Spring Boot and JPA. The API supports CRUD operations, search functionality, pagination and relationship management between movies, genres and actors.

## Table of contents
1. [Features](#Features)
2. [Technologies Used](#Technologies-Used)
3. [Setup Instructions](#Setup-Instructions)
4. [API Endpoints](#API-Endpoints)
    - [Genres](#Genres)
    - [Movies](#Movies)
    - [Actors](#Actors)
5. [Sample Data](#Sample-Data)
6. [Pagination and Filtering](#Pagination-and-Filtering)
7. [Error Handling](#Error-Handling)


## Features
- CRUD operations for Genres, Movies, and Actors.
- Pagination and filtering support for GET endpoints.
- Search functionality (e.g., search movies by title).
- Forced deletion for entities with relationships.
- Error handling for validation and resource-not-found scenarios.
- Relationships:
    - Many-to-Many between Movies and Genres.
    - Many-to-Many between Movies and Actors.

## Technologies Used
- Java 17+
- Spring Boot (v2.7+)
- Hibernate / JPA 
- SQLite (database)
- Postman (for testing)
- Maven (dependency management)

## Setup Instructions
- Clone the Repository:

```bash
git clone https://gitea.kood.tech/rasmussild/kmdb.git
cd kmdb
```
- Ensure Prerequisites:

    - Java 17+
    - Maven
    - SQLite
    - Configure the Database:

- SQLite is pre-configured in the **application.properties** file.
- Sample data will automatically populate when the application is run for the first time.
- Build and Run:

```bash

mvn clean install
mvn spring-boot:run
```
- Access the API:

    - Base URL: http://localhost:8080/api

- Test with Postman:

    - Import the included Postman collection (postman_collection.json).

## API Endpoints

### Genres
- GET `/api/genres` - Retrieve all genres (with pagination).
- GET `/api/genres/{id}` - Retrieve a specific genre by ID.
- POST `/api/genres` - Create a new genre.
- PATCH `/api/genres/{id}` - Update an existing genre.
- DELETE `/api/genres/{id}` - Delete a genre (supports `force=true` for forced deletion).
### Movies
- GET `/api/movies` - Retrieve all movies (with pagination).
- GET `/api/movies/{id}` - Retrieve a specific movie by ID.
- GET `/api/movies?genre={genreId}` - Retrieve movies by genre.
- GET `/api/movies?year={releaseYear}` - Retrieve movies by release year.
- GET `/api/movies/{id}/actors` - Retrieve actors for a specific movie.
- GET `api/movies/search?title={title}` - Retrieve movie by title or partial title 
- GET `api/movies?actor={id}` - Retrieve movie by actor id
- POST `/api/movies` - Create a new movie with genres and actors.
- PATCH `/api/movies/{id}` - Update an existing movie.
- DELETE `/api/movies/{id}` - Delete a movie.

### Actors
- GET `/api/actors` - Retrieve all actors (with pagination).
- GET `/api/actors/{id}` - Retrieve a specific actor by ID.
- GET `/api/actors?name={name}` - Search actors by name (case-insensitive).
- POST `/api/actors` - Create a new actor.
- PATCH `/api/actors/{id` - Update an existing actor.
- DELETE `/api/actors/{id}` - Delete an actor (supports `force=true` for forced deletion).

### Sample Data
The database includes:

- 5 Genres: Action, Comedy, Drama, Sci-Fi, Thriller.
- 20 Movies: Spanning multiple genres and release years.
- 15 Actors: Associated with various movies.

### Pagination and Filtering
GET endpoints that return collections support pagination.

- Example: `/api/movies?page=0&size=5`
- Returns 5 movies from the first page.

Filtering is supported for specific endpoints:

- `/api/movies?genre={genreId}`

- `/api/movies?year={releaseYear}`

- `/api/actors?name={name}`