package com.janchabik.moviego.service.impl;

import com.janchabik.moviego.service.FilmService;
import com.janchabik.moviego.domain.Film;
import com.janchabik.moviego.repository.FilmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Film}.
 */
@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    private final Logger log = LoggerFactory.getLogger(FilmServiceImpl.class);

    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    /**
     * Save a film.
     *
     * @param film the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Film save(Film film) {
        log.debug("Request to save Film : {}", film);
        return filmRepository.save(film);
    }

    /**
     * Get all the films.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Film> findAll() {
        log.debug("Request to get all Films");
        return filmRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the films with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Film> findAllWithEagerRelationships(Pageable pageable) {
        return filmRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one film by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Film> findOne(Long id) {
        log.debug("Request to get Film : {}", id);
        return filmRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the film by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Film : {}", id);
        filmRepository.deleteById(id);
    }
}
