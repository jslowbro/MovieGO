package com.janchabik.moviego.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Film.
 */
@Entity
@Table(name = "film")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "film")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Rating> ratings = new HashSet<>();

    @OneToMany(mappedBy = "film")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "film_person_container",
               joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "person_container_id", referencedColumnName = "id"))
    private Set<PersonContainer> personContainers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Film title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Film description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public Film ratings(Set<Rating> ratings) {
        this.ratings = ratings;
        return this;
    }

    public Film addRating(Rating rating) {
        this.ratings.add(rating);
        rating.setFilm(this);
        return this;
    }

    public Film removeRating(Rating rating) {
        this.ratings.remove(rating);
        rating.setFilm(null);
        return this;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public Film reviews(Set<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public Film addReview(Review review) {
        this.reviews.add(review);
        review.setFilm(this);
        return this;
    }

    public Film removeReview(Review review) {
        this.reviews.remove(review);
        review.setFilm(null);
        return this;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<PersonContainer> getPersonContainers() {
        return personContainers;
    }

    public Film personContainers(Set<PersonContainer> personContainers) {
        this.personContainers = personContainers;
        return this;
    }

    public Film addPersonContainer(PersonContainer personContainer) {
        this.personContainers.add(personContainer);
        personContainer.getFilms().add(this);
        return this;
    }

    public Film removePersonContainer(PersonContainer personContainer) {
        this.personContainers.remove(personContainer);
        personContainer.getFilms().remove(this);
        return this;
    }

    public void setPersonContainers(Set<PersonContainer> personContainers) {
        this.personContainers = personContainers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Film)) {
            return false;
        }
        return id != null && id.equals(((Film) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Film{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
