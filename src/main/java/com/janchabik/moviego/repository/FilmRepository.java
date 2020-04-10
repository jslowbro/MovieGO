package com.janchabik.moviego.repository;

import com.janchabik.moviego.domain.Film;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Film entity.
 */
@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query(value = "select distinct film from Film film left join fetch film.personContainers",
        countQuery = "select count(distinct film) from Film film")
    Page<Film> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct film from Film film left join fetch film.personContainers")
    List<Film> findAllWithEagerRelationships();

    @Query("select film from Film film left join fetch film.personContainers where film.id =:id")
    Optional<Film> findOneWithEagerRelationships(@Param("id") Long id);
}
