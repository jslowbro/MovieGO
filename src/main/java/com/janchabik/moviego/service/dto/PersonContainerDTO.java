package com.janchabik.moviego.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.janchabik.moviego.domain.enumeration.Role;

/**
 * A DTO for the {@link com.janchabik.moviego.domain.PersonContainer} entity.
 */
public class PersonContainerDTO implements Serializable {
    
    private Long id;

    private Role role;


    private Long personId;

    private Long filmId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonContainerDTO personContainerDTO = (PersonContainerDTO) o;
        if (personContainerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personContainerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PersonContainerDTO{" +
            "id=" + getId() +
            ", role='" + getRole() + "'" +
            ", personId=" + getPersonId() +
            ", filmId=" + getFilmId() +
            "}";
    }
}
