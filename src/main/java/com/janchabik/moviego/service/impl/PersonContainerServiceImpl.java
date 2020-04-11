package com.janchabik.moviego.service.impl;

import com.janchabik.moviego.service.PersonContainerService;
import com.janchabik.moviego.domain.PersonContainer;
import com.janchabik.moviego.repository.PersonContainerRepository;
import com.janchabik.moviego.service.dto.PersonContainerDTO;
import com.janchabik.moviego.service.mapper.PersonContainerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PersonContainer}.
 */
@Service
@Transactional
public class PersonContainerServiceImpl implements PersonContainerService {

    private final Logger log = LoggerFactory.getLogger(PersonContainerServiceImpl.class);

    private final PersonContainerRepository personContainerRepository;

    private final PersonContainerMapper personContainerMapper;

    public PersonContainerServiceImpl(PersonContainerRepository personContainerRepository, PersonContainerMapper personContainerMapper) {
        this.personContainerRepository = personContainerRepository;
        this.personContainerMapper = personContainerMapper;
    }

    /**
     * Save a personContainer.
     *
     * @param personContainerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PersonContainerDTO save(PersonContainerDTO personContainerDTO) {
        log.debug("Request to save PersonContainer : {}", personContainerDTO);
        PersonContainer personContainer = personContainerMapper.toEntity(personContainerDTO);
        personContainer = personContainerRepository.save(personContainer);
        return personContainerMapper.toDto(personContainer);
    }

    /**
     * Get all the personContainers.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PersonContainerDTO> findAll() {
        log.debug("Request to get all PersonContainers");
        return personContainerRepository.findAll().stream()
            .map(personContainerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one personContainer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PersonContainerDTO> findOne(Long id) {
        log.debug("Request to get PersonContainer : {}", id);
        return personContainerRepository.findById(id)
            .map(personContainerMapper::toDto);
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
