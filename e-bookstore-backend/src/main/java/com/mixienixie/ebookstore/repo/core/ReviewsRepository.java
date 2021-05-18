package com.mixienixie.ebookstore.repo.core;

import com.mixienixie.ebookstore.repo.core.entity.BookEntity;
import com.mixienixie.ebookstore.repo.core.entity.ReviewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ReviewEntity repository
 *
 * @author mdjukanovic
 */
public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Long> {

    /**
     * Finds reviews for the book
     * @param book book
     * @param pageable pageable
     * @return pageable of reviews
     */
    Page<ReviewsEntity> findReviewsEntitiesByBook(BookEntity book, Pageable pageable);

    /**
     * Finds reviews by the user who made them
     * @param userId id of the user
     * @param pageable pageable
     * @return pageable of the reviews
     */
    Page<ReviewsEntity> findReviewsEntitiesByUserId(Long userId, Pageable pageable);

    /**
     * Finds reviews with the grade less than
     * @param grade grade of the review
     * @param pageable pageable
     * @return pageable of the reviews
     */
    Page<ReviewsEntity> findReviewsEntitiesByGradeLessThan(int grade, Pageable pageable);

    /**
     * Finds reviews with the grade less than
     * @param grade grade of the review
     * @param pageable pageable
     * @return pageable of the reviews
     */
    Page<ReviewsEntity> findReviewsEntitiesByGradeGreaterThan(int grade, Pageable pageable);
}
