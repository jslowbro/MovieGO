package com.janchabik.moviego.service.impl;

import com.janchabik.moviego.service.FilmService;
import com.janchabik.moviego.domain.Film;
import com.janchabik.moviego.repository.FilmRepository;
import com.janchabik.moviego.service.dto.FilmDTO;
import com.janchabik.moviego.service.mapper.FilmMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Film}.
 */
@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    private final Logger log = LoggerFactory.getLogger(FilmServiceImpl.class);

    private final FilmRepository filmRepository;

    private final FilmMapper filmMapper;

    public FilmServiceImpl(FilmRepository filmRepository, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    /**
     * Save a film.
     *
     * @param filmDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FilmDTO save(FilmDTO filmDTO) {
        log.debug("Request to save Film : {}", filmDTO);
        Film film = filmMapper.toEntity(filmDTO);
        film = filmRepository.save(film);
        return filmMapper.toDto(film);
    }

    /**
     * Get all the films.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FilmDTO> findAll() {
        log.debug("Request to get all Films");
        return filmRepository.findAll().stream()
            .map(filmMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one film by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FilmDTO> findOne(Long id) {
        log.debug("Request to get Film : {}", id);
        return filmRepository.findById(id)
            .map(filmMapper::toDto);
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
