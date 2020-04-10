package com.janchabik.moviego.service.impl;

import com.janchabik.moviego.service.PersonContainerService;
import com.janchabik.moviego.domain.PersonContainer;
import com.janchabik.moviego.repository.PersonContainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link PersonContainer}.
 */
@Service
@Transactional
public class PersonContainerServiceImpl implements PersonContainerService {

    private final Logger log = LoggerFactory.getLogger(PersonContainerServiceImpl.class);

    private final PersonContainerRepository personContainerRepository;

    public PersonContainerServiceImpl(PersonContainerRepository personContainerRepository) {
        this.personContainerRepository = personContainerRepository;
    }

    /**
     * Save a personContainer.
     *
     * @param personContainer the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PersonContainer save(PersonContainer personContainer) {
        log.debug("Request to save PersonContainer : {}", personContainer);
        return personContainerRepository.save(personContainer);
    }

    /**
     * Get all the personContainers.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PersonContainer> findAll() {
        log.debug("Request to get all PersonContainers");
        return personContainerRepository.findAll();
    }

    /**
     * Get one personContainer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PersonContainer> findOne(Long id) {
        log.debug("Request to get PersonContainer : {}", id);
        return personContainerRepository.findById(id);
    }

    /**
     * Delete the personContainer by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PersonContainer : {}", id);
        personContainerRepository.deleteById(id);
    }
}
