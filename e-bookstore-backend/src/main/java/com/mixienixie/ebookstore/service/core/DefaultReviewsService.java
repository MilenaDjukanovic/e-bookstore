package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreateReviewsRequest;
import com.mixienixie.ebookstore.repo.core.ReviewsRepository;
import com.mixienixie.ebookstore.repo.core.entity.ReviewsDto;
import com.mixienixie.ebookstore.repo.core.entity.ReviewsEntity;
import com.mixienixie.ebookstore.service.ReviewsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 *
 * Default implementation of the Reviews Service
 * @author mdjukanovic
 */
@Service
@AllArgsConstructor
public class DefaultReviewsService implements ReviewsService {

    /** Reviews Repository */
    private final ReviewsRepository reviewsRepository;

    /** Reviews View Mapper */
    private final ReviewsCreateMapper reviewsCreateMapper;

    /** Reviews Create Mapper */
    private final ReviewsViewMapper reviewsViewMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewsDto create(CreateReviewsRequest createReviewsRequest) {
        ReviewsEntity reviewsEntity = this.reviewsCreateMapper.toEntity(createReviewsRequest);

        reviewsEntity = this.reviewsRepository.save(reviewsEntity);

        return this.reviewsViewMapper.toDto(reviewsEntity);
    }
}
