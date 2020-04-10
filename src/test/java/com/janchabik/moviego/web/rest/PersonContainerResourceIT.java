package com.janchabik.moviego.web.rest;

import com.janchabik.moviego.MovieGoApp;
import com.janchabik.moviego.config.TestSecurityConfiguration;
import com.janchabik.moviego.domain.PersonContainer;
import com.janchabik.moviego.repository.PersonContainerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.janchabik.moviego.domain.enumeration.Role;
/**
 * Integration tests for the {@link PersonContainerResource} REST controller.
 */
@SpringBootTest(classes = { MovieGoApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class PersonContainerResourceIT {

    private static final Role DEFAULT_ROLE = Role.ACTOR;
    private static final Role UPDATED_ROLE = Role.DIRECTOR;

    @Autowired
    private PersonContainerRepository personContainerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonContainerMockMvc;

    private PersonContainer personContainer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonContainer createEntity(EntityManager em) {
        PersonContainer personContainer = new PersonContainer()
            .role(DEFAULT_ROLE);
        return personContainer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonContainer createUpdatedEntity(EntityManager em) {
        PersonContainer personContainer = new PersonContainer()
            .role(UPDATED_ROLE);
        return personContainer;
    }

    @BeforeEach
    public void initTest() {
        personContainer = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonContainer() throws Exception {
        int databaseSizeBeforeCreate = personContainerRepository.findAll().size();

        // Create the PersonContainer
        restPersonContainerMockMvc.perform(post("/api/person-containers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personContainer)))
            .andExpect(status().isCreated());

        // Validate the PersonContainer in the database
        List<PersonContainer> personContainerList = personContainerRepository.findAll();
        assertThat(personContainerList).hasSize(databaseSizeBeforeCreate + 1);
        PersonContainer testPersonContainer = personContainerList.get(personContainerList.size() - 1);
        assertThat(testPersonContainer.getRole()).isEqualTo(DEFAULT_ROLE);
    }

    @Test
    @Transactional
    public void createPersonContainerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personContainerRepository.findAll().size();

        // Create the PersonContainer with an existing ID
        personContainer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonContainerMockMvc.perform(post("/api/person-containers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personContainer)))
            .andExpect(status().isBadRequest());

        // Validate the PersonContainer in the database
        List<PersonContainer> personContainerList = personContainerRepository.findAll();
        assertThat(personContainerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPersonContainers() throws Exception {
        // Initialize the database
        personContainerRepository.saveAndFlush(personContainer);

        // Get all the personContainerList
        restPersonContainerMockMvc.perform(get("/api/person-containers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personContainer.getId().intValue())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())));
    }
    
    @Test
    @Transactional
    public void getPersonContainer() throws Exception {
        // Initialize the database
        personContainerRepository.saveAndFlush(personContainer);

        // Get the personContainer
        restPersonContainerMockMvc.perform(get("/api/person-containers/{id}", personContainer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(personContainer.getId().intValue()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPersonContainer() throws Exception {
        // Get the personContainer
        restPersonContainerMockMvc.perform(get("/api/person-containers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonContainer() throws Exception {
        // Initialize the database
        personContainerRepository.saveAndFlush(personContainer);

        int databaseSizeBeforeUpdate = personContainerRepository.findAll().size();

        // Update the personContainer
        PersonContainer updatedPersonContainer = personContainerRepository.findById(personContainer.getId()).get();
        // Disconnect from session so that the updates on updatedPersonContainer are not directly saved in db
        em.detach(updatedPersonContainer);
        updatedPersonContainer
            .role(UPDATED_ROLE);

        restPersonContainerMockMvc.perform(put("/api/person-containers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPersonContainer)))
            .andExpect(status().isOk());

        // Validate the PersonContainer in the database
        List<PersonContainer> personContainerList = personContainerRepository.findAll();
        assertThat(personContainerList).hasSize(databaseSizeBeforeUpdate);
        PersonContainer testPersonContainer = personContainerList.get(personContainerList.size() - 1);
        assertThat(testPersonContainer.getRole()).isEqualTo(UPDATED_ROLE);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonContainer() throws Exception {
        int databaseSizeBeforeUpdate = personContainerRepository.findAll().size();

        // Create the PersonContainer

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonContainerMockMvc.perform(put("/api/person-containers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personContainer)))
            .andExpect(status().isBadRequest());

        // Validate the PersonContainer in the database
        List<PersonContainer> personContainerList = personContainerRepository.findAll();
        assertThat(personContainerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersonContainer() throws Exception {
        // Initialize the database
        personContainerRepository.saveAndFlush(personContainer);

        int databaseSizeBeforeDelete = personContainerRepository.findAll().size();

        // Delete the personContainer
        restPersonContainerMockMvc.perform(delete("/api/person-containers/{id}", personContainer.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PersonContainer> personContainerList = personContainerRepository.findAll();
        assertThat(personContainerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
