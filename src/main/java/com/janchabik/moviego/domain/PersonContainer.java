package com.janchabik.moviego.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

import com.janchabik.moviego.domain.enumeration.Role;

/**
 * A PersonContainer.
 */
@Entity
@Table(name = "person_container")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PersonContainer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "film")
    private String film;

    @ManyToOne
    @JsonIgnoreProperties("personContainers")
    private Person person;

    @ManyToMany(mappedBy = "personContainers")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Film> films = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public PersonContainer role(Role role) {
        this.role = role;
        return this;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFilm() {
        return film;
    }

    public PersonContainer film(String film) {
        this.film = film;
        return this;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public Person getPerson() {
        return person;
    }

    public PersonContainer person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public PersonContainer films(Set<Film> films) {
        this.films = films;
        return this;
    }

    public PersonContainer addFilm(Film film) {
        this.films.add(film);
        film.getPersonContainers().add(this);
        return this;
    }

    public PersonContainer removeFilm(Film film) {
        this.films.remove(film);
        film.getPersonContainers().remove(this);
        return this;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonContainer)) {
            return false;
        }
        return id != null && id.equals(((PersonContainer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PersonContainer{" +
            "id=" + getId() +
            ", role='" + getRole() + "'" +
            ", film='" + getFilm() + "'" +
            "}";
    }
}
