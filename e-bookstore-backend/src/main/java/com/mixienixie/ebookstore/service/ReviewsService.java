package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.core.requests.CreateReviewsRequest;
import com.mixienixie.ebookstore.repo.core.entity.ReviewsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for Reviews related operations
 *
 * @author mdjukanovic
 */
public interface ReviewsService {

    /**
     * Creates reviews from the createReviewsRequest
     * @param createReviewsRequest request for review creation containing review details
     * @return Review object for viewing if successful
     */
    ReviewsDto create(CreateReviewsRequest createReviewsRequest);

    Page<ReviewsDto> findAll(Pageable pageable);
}
