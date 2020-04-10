package com.janchabik.moviego.repository;

import com.janchabik.moviego.domain.Rating;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Rating entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("select rating from Rating rating where rating.user.login = ?#{principal.preferredUsername}")
    List<Rating> findByUserIsCurrentUser();
}
