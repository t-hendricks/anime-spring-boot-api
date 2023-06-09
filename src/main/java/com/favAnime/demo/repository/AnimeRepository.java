package com.favAnime.demo.repository;

import com.favAnime.demo.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    Anime findByName(String animeName);

    Anime findByNameAndUserId(String animeName, Long userId);
}
