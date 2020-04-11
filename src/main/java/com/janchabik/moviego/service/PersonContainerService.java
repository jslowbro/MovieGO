package com.janchabik.moviego.service;

import com.janchabik.moviego.service.dto.PersonContainerDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.janchabik.moviego.domain.PersonContainer}.
 */
public interface PersonContainerService {

    /**
     * Save a personContainer.
     *
     * @param personContainerDTO the entity to save.
     * @return the persisted entity.
     */
    PersonContainerDTO save(PersonContainerDTO personContainerDTO);

    /**
     * Get all the personContainers.
     *
     * @return the list of entities.
     */
    List<PersonContainerDTO> findAll();

    /**
     * Get the "id" personContainer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PersonContainerDTO> findOne(Long id);

    /**
     * Delete the "id" personContainer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
