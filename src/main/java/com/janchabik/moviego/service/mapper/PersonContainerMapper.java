package com.janchabik.moviego.service.mapper;


import com.janchabik.moviego.domain.*;
import com.janchabik.moviego.service.dto.PersonContainerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PersonContainer} and its DTO {@link PersonContainerDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class, FilmMapper.class})
public interface PersonContainerMapper extends EntityMapper<PersonContainerDTO, PersonContainer> {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "film.id", target = "filmId")
    PersonContainerDTO toDto(PersonContainer personContainer);

    @Mapping(source = "personId", target = "person")
    @Mapping(source = "filmId", target = "film")
    PersonContainer toEntity(PersonContainerDTO personContainerDTO);

    default PersonContainer fromId(Long id) {
        if (id == null) {
            return null;
        }
        PersonContainer personContainer = new PersonContainer();
        personContainer.setId(id);
        return personContainer;
    }
}
