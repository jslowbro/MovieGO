package com.janchabik.moviego.web.rest;

import com.janchabik.moviego.service.ReviewService;
import com.janchabik.moviego.web.rest.errors.BadRequestAlertException;
import com.janchabik.moviego.service.dto.ReviewDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.janchabik.moviego.domain.Review}.
 */
@RestController
@RequestMapping("/api")
public class ReviewResource {

    private final Logger log = LoggerFactory.getLogger(ReviewResource.class);

    private static final String ENTITY_NAME = "review";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReviewService reviewService;

    public ReviewResource(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * {@code POST  /reviews} : Create a new review.
     *
     * @param reviewDTO the reviewDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reviewDTO, or with status {@code 400 (Bad Request)} if the review has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reviews")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO) throws URISyntaxException {
        log.debug("REST request to save Review : {}", reviewDTO);
        if (reviewDTO.getId() != null) {
            throw new BadRequestAlertException("A new review cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReviewDTO result = reviewService.save(reviewDTO);
        return ResponseEntity.created(new URI("/api/reviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reviews} : Updates an existing review.
     *
     * @param reviewDTO the reviewDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reviewDTO,
     * or with status {@code 400 (Bad Request)} if the reviewDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reviewDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reviews")
    public ResponseEntity<ReviewDTO> updateReview(@RequestBody ReviewDTO reviewDTO) throws URISyntaxException {
        log.debug("REST request to update Review : {}", reviewDTO);
        if (reviewDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReviewDTO result = reviewService.save(reviewDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, reviewDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /reviews} : get all the reviews.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reviews in body.
     */
    @GetMapping("/reviews")
    public List<ReviewDTO> getAllReviews() {
        log.debug("REST request to get all Reviews");
        return reviewService.findAll();
    }

    /**
     * {@code GET  /reviews/:id} : get the "id" review.
     *
     * @param id the id of the reviewDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reviewDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable Long id) {
        log.debug("REST request to get Review : {}", id);
        Optional<ReviewDTO> reviewDTO = reviewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reviewDTO);
    }

    /**
     * {@code DELETE  /reviews/:id} : delete the "id" review.
     *
     * @param id the id of the reviewDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        log.debug("REST request to delete Review : {}", id);
        reviewService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
