package com.janchabik.moviego.service.mapper;


import com.janchabik.moviego.domain.*;
import com.janchabik.moviego.service.dto.RatingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Rating} and its DTO {@link RatingDTO}.
 */
@Mapper(componentModel = "spring", uses = {FilmMapper.class, UserMapper.class})
public interface RatingMapper extends EntityMapper<RatingDTO, Rating> {

    @Mapping(source = "film.id", target = "filmId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    RatingDTO toDto(Rating rating);

    @Mapping(source = "filmId", target = "film")
    @Mapping(source = "userId", target = "user")
    Rating toEntity(RatingDTO ratingDTO);

    default Rating fromId(Long id) {
        if (id == null) {
            return null;
        }
        Rating rating = new Rating();
        rating.setId(id);
        return rating;
    }
}
