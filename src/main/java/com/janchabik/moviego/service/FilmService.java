package com.janchabik.moviego.service;

import com.janchabik.moviego.service.dto.FilmDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.janchabik.moviego.domain.Film}.
 */
public interface FilmService {

    /**
     * Save a film.
     *
     * @param filmDTO the entity to save.
     * @return the persisted entity.
     */
    FilmDTO save(FilmDTO filmDTO);

    /**
     * Get all the films.
     *
     * @return the list of entities.
     */
    List<FilmDTO> findAll();

    /**
     * Get the "id" film.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FilmDTO> findOne(Long id);

    /**
     * Delete the "id" film.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
