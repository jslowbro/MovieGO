package com.janchabik.moviego.repository;

import com.janchabik.moviego.domain.Review;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Review entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select review from Review review where review.user.login = ?#{principal.preferredUsername}")
    List<Review> findByUserIsCurrentUser();
}
