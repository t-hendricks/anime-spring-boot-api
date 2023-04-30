package com.favAnime.demo.service;

import com.favAnime.demo.exception.InformationExistException;
import com.favAnime.demo.exception.InformationNotFoundException;
import com.favAnime.demo.model.Genre;
import com.favAnime.demo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private GenreRepository genreRepository;

    @Autowired // setter-based dependency injection creates an instance of GenreRepository
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
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
}
