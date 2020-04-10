package com.janchabik.moviego.repository;

import com.janchabik.moviego.domain.PersonContainer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the PersonContainer entity.
 */
@Repository
public interface PersonContainerRepository extends JpaRepository<PersonContainer, Long> {

    @Query(value = "select distinct personContainer from PersonContainer personContainer left join fetch personContainer.films",
        countQuery = "select count(distinct personContainer) from PersonContainer personContainer")
    Page<PersonContainer> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct personContainer from PersonContainer personContainer left join fetch personContainer.films")
    List<PersonContainer> findAllWithEagerRelationships();

    @Query("select personContainer from PersonContainer personContainer left join fetch personContainer.films where personContainer.id =:id")
    Optional<PersonContainer> findOneWithEagerRelationships(@Param("id") Long id);
}
