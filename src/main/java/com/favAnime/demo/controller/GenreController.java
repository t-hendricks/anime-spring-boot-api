package com.favAnime.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // annotated with @ResponseBody; return object in JSON
@RequestMapping(path = "/api")
public class GenreController {

    // sample GET endpoint
    // http://localhost:9090/api/genres
    @GetMapping(path = "/genres")
    public String getGenres() {
        return "get all genres";
    }
}
