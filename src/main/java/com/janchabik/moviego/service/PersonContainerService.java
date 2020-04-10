package com.janchabik.moviego.service;

import com.janchabik.moviego.domain.PersonContainer;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link PersonContainer}.
 */
public interface PersonContainerService {

    /**
     * Save a personContainer.
     *
     * @param personContainer the entity to save.
     * @return the persisted entity.
     */
    PersonContainer save(PersonContainer personContainer);

    /**
     * Get all the personContainers.
     *
     * @return the list of entities.
     */
    List<PersonContainer> findAll();

    /**
     * Get the "id" personContainer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PersonContainer> findOne(Long id);

    /**
     * Delete the "id" personContainer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
