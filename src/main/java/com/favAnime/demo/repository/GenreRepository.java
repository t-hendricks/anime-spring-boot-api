package com.favAnime.demo.repository;

import com.favAnime.demo.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    // select found Genre object by given Genre name
    Genre findByName(String genreName);
}
