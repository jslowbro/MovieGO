package com.janchabik.moviego.web.rest;

import com.janchabik.moviego.domain.PersonContainer;
import com.janchabik.moviego.repository.PersonContainerRepository;
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
 * REST controller for managing {@link com.janchabik.moviego.domain.PersonContainer}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PersonContainerResource {

    private final Logger log = LoggerFactory.getLogger(PersonContainerResource.class);

    private static final String ENTITY_NAME = "personContainer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonContainerRepository personContainerRepository;

    public PersonContainerResource(PersonContainerRepository personContainerRepository) {
        this.personContainerRepository = personContainerRepository;
    }

    /**
     * {@code POST  /person-containers} : Create a new personContainer.
     *
     * @param personContainer the personContainer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personContainer, or with status {@code 400 (Bad Request)} if the personContainer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/person-containers")
    public ResponseEntity<PersonContainer> createPersonContainer(@RequestBody PersonContainer personContainer) throws URISyntaxException {
        log.debug("REST request to save PersonContainer : {}", personContainer);
        if (personContainer.getId() != null) {
            throw new BadRequestAlertException("A new personContainer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonContainer result = personContainerRepository.save(personContainer);
        return ResponseEntity.created(new URI("/api/person-containers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /person-containers} : Updates an existing personContainer.
     *
     * @param personContainer the personContainer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personContainer,
     * or with status {@code 400 (Bad Request)} if the personContainer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personContainer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/person-containers")
    public ResponseEntity<PersonContainer> updatePersonContainer(@RequestBody PersonContainer personContainer) throws URISyntaxException {
        log.debug("REST request to update PersonContainer : {}", personContainer);
        if (personContainer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PersonContainer result = personContainerRepository.save(personContainer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, personContainer.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /person-containers} : get all the personContainers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personContainers in body.
     */
    @GetMapping("/person-containers")
    public List<PersonContainer> getAllPersonContainers() {
        log.debug("REST request to get all PersonContainers");
        return personContainerRepository.findAll();
    }

    /**
     * {@code GET  /person-containers/:id} : get the "id" personContainer.
     *
     * @param id the id of the personContainer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personContainer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/person-containers/{id}")
    public ResponseEntity<PersonContainer> getPersonContainer(@PathVariable Long id) {
        log.debug("REST request to get PersonContainer : {}", id);
        Optional<PersonContainer> personContainer = personContainerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(personContainer);
    }

    /**
     * {@code DELETE  /person-containers/:id} : delete the "id" personContainer.
     *
     * @param id the id of the personContainer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/person-containers/{id}")
    public ResponseEntity<Void> deletePersonContainer(@PathVariable Long id) {
        log.debug("REST request to delete PersonContainer : {}", id);
        personContainerRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
