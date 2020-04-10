package com.janchabik.moviego.web.rest;

import com.janchabik.moviego.domain.Film;
import com.janchabik.moviego.repository.FilmRepository;
import com.janchabik.moviego.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.janchabik.moviego.domain.Film}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FilmResource {

    private final Logger log = LoggerFactory.getLogger(FilmResource.class);

    private static final String ENTITY_NAME = "film";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FilmRepository filmRepository;

    public FilmResource(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    /**
     * {@code POST  /films} : Create a new film.
     *
     * @param film the film to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new film, or with status {@code 400 (Bad Request)} if the film has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/films")
    public ResponseEntity<Film> createFilm(@RequestBody Film film) throws URISyntaxException {
        log.debug("REST request to save Film : {}", film);
        if (film.getId() != null) {
            throw new BadRequestAlertException("A new film cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Film result = filmRepository.save(film);
        return ResponseEntity.created(new URI("/api/films/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /films} : Updates an existing film.
     *
     * @param film the film to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated film,
     * or with status {@code 400 (Bad Request)} if the film is not valid,
     * or with status {@code 500 (Internal Server Error)} if the film couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/films")
    public ResponseEntity<Film> updateFilm(@RequestBody Film film) throws URISyntaxException {
        log.debug("REST request to update Film : {}", film);
        if (film.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Film result = filmRepository.save(film);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, film.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /films} : get all the films.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of films in body.
     */
    @GetMapping("/films")
    public List<Film> getAllFilms() {
        log.debug("REST request to get all Films");
        return filmRepository.findAll();
    }

    /**
     * {@code GET  /films/:id} : get the "id" film.
     *
     * @param id the id of the film to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the film, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/films/{id}")
    public ResponseEntity<Film> getFilm(@PathVariable Long id) {
        log.debug("REST request to get Film : {}", id);
        Optional<Film> film = filmRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(film);
    }

    /**
     * {@code DELETE  /films/:id} : delete the "id" film.
     *
     * @param id the id of the film to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/films/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        log.debug("REST request to delete Film : {}", id);
        filmRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
