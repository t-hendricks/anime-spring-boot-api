package com.favAnime.demo.controller;

import com.favAnime.demo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // annotated with @ResponseBody; return object in JSON
@RequestMapping(path = "/api")
public class GenreController {

    private GenreRepository genreRepository;

    @Autowired // setter-based dependency injection creates an instance of GenreRepository
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    // sample GET endpoint
    // http://localhost:9090/api/genres
    @GetMapping(path = "/genres")
    public String getGenres() {
        return "get all genres";
    }

    // sample GET endpoint
    // http://localhost:9090/api/genres/1
    @GetMapping(path = "/genres/{genreId}")
    public String getGenreById(@PathVariable Long genreId) {
        return "get genre with Id " + genreId;
    }

    // sample POST endpoint
    // http://localhost:9090/api/genres
    @PostMapping(path = "/genres")
    public String createGenre(@RequestBody String body) {
        return "creating a new genre with " + body;
    }

    // sample PUT endpoint
    // http://localhost:9090/api/genres/1
    @PutMapping(path = "/genres/{genreId}")
    public String updateGenre(@PathVariable Long genreId, @RequestBody String body) {
        return "updating genre " + genreId + " with " + body;
    }

    // sample DELETE endpoint
    // http://localhost:9090/api/genres/1
    @DeleteMapping(path = "/genres/{genreId}")
    public String deleteGenre(@PathVariable Long genreId) {
        return "delete genre " + genreId;
    }
}
