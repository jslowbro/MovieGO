package com.janchabik.moviego.service.mapper;


import com.janchabik.moviego.domain.*;
import com.janchabik.moviego.service.dto.ReviewDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Review} and its DTO {@link ReviewDTO}.
 */
@Mapper(componentModel = "spring", uses = {FilmMapper.class, UserMapper.class})
public interface ReviewMapper extends EntityMapper<ReviewDTO, Review> {

    @Mapping(source = "film.id", target = "filmId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    ReviewDTO toDto(Review review);

    @Mapping(source = "filmId", target = "film")
    @Mapping(source = "userId", target = "user")
    Review toEntity(ReviewDTO reviewDTO);

    default Review fromId(Long id) {
        if (id == null) {
            return null;
        }
        Review review = new Review();
        review.setId(id);
        return review;
    }
}
