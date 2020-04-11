package com.janchabik.moviego.web.rest;

import com.janchabik.moviego.service.PersonContainerService;
import com.janchabik.moviego.web.rest.errors.BadRequestAlertException;
import com.janchabik.moviego.service.dto.PersonContainerDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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
public class PersonContainerResource {

    private final Logger log = LoggerFactory.getLogger(PersonContainerResource.class);

    private static final String ENTITY_NAME = "personContainer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonContainerService personContainerService;

    public PersonContainerResource(PersonContainerService personContainerService) {
        this.personContainerService = personContainerService;
    }

    /**
     * {@code POST  /person-containers} : Create a new personContainer.
     *
     * @param personContainerDTO the personContainerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personContainerDTO, or with status {@code 400 (Bad Request)} if the personContainer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/person-containers")
    public ResponseEntity<PersonContainerDTO> createPersonContainer(@RequestBody PersonContainerDTO personContainerDTO) throws URISyntaxException {
        log.debug("REST request to save PersonContainer : {}", personContainerDTO);
        if (personContainerDTO.getId() != null) {
            throw new BadRequestAlertException("A new personContainer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonContainerDTO result = personContainerService.save(personContainerDTO);
        return ResponseEntity.created(new URI("/api/person-containers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /person-containers} : Updates an existing personContainer.
     *
     * @param personContainerDTO the personContainerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personContainerDTO,
     * or with status {@code 400 (Bad Request)} if the personContainerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personContainerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/person-containers")
    public ResponseEntity<PersonContainerDTO> updatePersonContainer(@RequestBody PersonContainerDTO personContainerDTO) throws URISyntaxException {
        log.debug("REST request to update PersonContainer : {}", personContainerDTO);
        if (personContainerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PersonContainerDTO result = personContainerService.save(personContainerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, personContainerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /person-containers} : get all the personContainers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personContainers in body.
     */
    @GetMapping("/person-containers")
    public List<PersonContainerDTO> getAllPersonContainers() {
        log.debug("REST request to get all PersonContainers");
        return personContainerService.findAll();
    }

    /**
     * {@code GET  /person-containers/:id} : get the "id" personContainer.
     *
     * @param id the id of the personContainerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personContainerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/person-containers/{id}")
    public ResponseEntity<PersonContainerDTO> getPersonContainer(@PathVariable Long id) {
        log.debug("REST request to get PersonContainer : {}", id);
        Optional<PersonContainerDTO> personContainerDTO = personContainerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(personContainerDTO);
    }

    /**
     * {@code DELETE  /person-containers/:id} : delete the "id" personContainer.
     *
     * @param id the id of the personContainerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/person-containers/{id}")
    public ResponseEntity<Void> deletePersonContainer(@PathVariable Long id) {
        log.debug("REST request to delete PersonContainer : {}", id);
        personContainerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
