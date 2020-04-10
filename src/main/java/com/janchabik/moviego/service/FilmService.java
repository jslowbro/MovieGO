package com.janchabik.moviego.service;

import com.janchabik.moviego.domain.Film;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Film}.
 */
public interface FilmService {

    /**
     * Save a film.
     *
     * @param film the entity to save.
     * @return the persisted entity.
     */
    Film save(Film film);

    /**
     * Get all the films.
     *
     * @return the list of entities.
     */
    List<Film> findAll();

    /**
     * Get all the films with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<Film> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" film.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Film> findOne(Long id);

    /**
     * Delete the "id" film.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
