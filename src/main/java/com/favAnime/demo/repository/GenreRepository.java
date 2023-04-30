package com.favAnime.demo.repository;

import com.favAnime.demo.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    // select found Genre object by given Genre name
    Genre findByName(String genreName);

    // get all genres that belong to logged-in user
    List<Genre> findByUserId(Long userId);

    // get genre by id and user id
   Genre findByIdAndUserId(Long categoryId, Long userId);

    // trying to find genre with name and user id
    Genre findByUserIdAndName(Long userId, String categoryName);
}
