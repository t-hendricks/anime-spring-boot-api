package com.favAnime.demo.service;

import com.favAnime.demo.exception.InformationExistException;
import com.favAnime.demo.exception.InformationNotFoundException;
import com.favAnime.demo.model.Anime;
import com.favAnime.demo.model.Genre;
import com.favAnime.demo.repository.AnimeRepository;
import com.favAnime.demo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private GenreRepository genreRepository;
    private AnimeRepository animeRepository;

    @Autowired // setter-based dependency injection creates an instance of GenreRepository
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Autowired // sett-based dependency injection creates an instance of AnimeRepository
    public void setAnimeRepository(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    /**
     * Gets all existing genres from the repository.
     *
     * @return List of Genre Objects
     */
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    /**
     * Gets a specific existing genre from the repository
     * based on the given genre id.
     *
     * @param genreId {Long}
     * @return Genre {Object}
     * @throws InformationNotFoundException
     */
    public Genre getGenreById(Long genreId) {
        try {
            Optional<Genre> genre = genreRepository.findById(genreId); // findById() returns Optional
            return genre.get();
        } catch (RuntimeException e) {
            throw new InformationNotFoundException("Genre with id " + genreId + " not found ");
        }
    }

    /**
     * Adds a new genre into the repository.
     *
     * @param genreObject {Object}
     * @return Genre {Object}
     * @throws InformationExistException
     */
    public Genre createGenre(Genre genreObject) {
        Genre genre = genreRepository.findByName(genreObject.getName());
        if (genre != null) {
            throw new InformationExistException("Genre with the name " + genre.getName() + " already exist ");
        } else {
            return genreRepository.save(genreObject);
        }
    }

    /**
     * Updates a specific existing genre in the
     * repository based on a given id.
     *
     * @param genreId {Long}
     * @param genreObject {Object}
     * @return Genre {Object}
     */
    public Genre updateGenre(Long genreId, Genre genreObject) {
        // invoke GET endpoint to handle exception
        Genre genre = getGenreById(genreId);
        genre.setName(genreObject.getName());
        return genreRepository.save(genre);
    }

    /**
     * Deletes a specific existing genre from the
     * repository based on a given id.
     *
     * @param genreId {Long}
     * @return Genre {Object}
     */
    public Genre deleteGenre(Long genreId) {
        // invoke GET endpoint to handle exception
        Genre genre = getGenreById(genreId);
        genreRepository.delete(genre);
        return genre;
    }

    /**
     * Sets a new anime into a genre then adds
     * the anime to the repository.
     *
     * @param genreId {Long}
     * @param animeObject {Object}
     * @return Anime {Object}
     * @throws InformationExistException
     */
    public Anime createGenreAnime(Long genreId, Anime animeObject) {
        // invoke GET endpoint to handle exception
        Genre genre = getGenreById(genreId);
        boolean animeExist = genre.getAnime().stream().anyMatch(a -> a.getName().equals(animeObject.getName()));
        if (animeExist) {
            throw new InformationExistException("Anime with the name " + animeObject.getName() + " already exist ");
        } else {
            animeObject.setGenre(genre);
            return animeRepository.save(animeObject);
        }
    }

    /**
     * Get all existing anime from specific
     * genre inside the respository.
     *
     * @param genreId {Long}
     * @return List of Anime Objects
     */
    public List<Anime> getGenreAnime(Long genreId) {
        // invoke GET endpoint to handle exception
        Genre genre = getGenreById(genreId);
        return genre.getAnime();
    }

    /**
     * Get a specific anime from a specific genre
     * in the repository.
     *
     * @param animeId {Long}
     * @return Anime {Object}
     * @throws InformationNotFoundException
     */
    public Anime getGenreAnimeById(Long genreId, Long animeId) {
        // invoke GET endpoint to handle exception
        Genre genre = getGenreById(genreId);
        try {
           Optional<Anime> anime = genre.getAnime().stream().filter(a -> a.getId().equals(animeId)).findFirst();
           return anime.get();
        } catch (RuntimeException e) {
            throw new InformationNotFoundException("Anime with id " + animeId + " not found ");
        }
    }

    /**
     * Updates a specific anime inside a specific
     * genre based on given genre id, anime id, and
     * anime body.
     *
     * @param genreId {Long}
     * @param animeId {Long}
     * @param animeObject {Object}
     * @return Anime {Object}
     */
    public Anime updateGenreAnime(Long genreId, Long animeId, Anime animeObject) {
        // invoke GET endpoint to handle exception
        Anime anime = getGenreAnimeById(genreId, animeId);
        anime.setName(animeObject.getName());
        anime.setDescription(animeObject.getDescription());
        return animeRepository.save(anime);
    }
}
