package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreateReviewsRequest;
import com.mixienixie.ebookstore.repo.core.entity.ReviewsEntity;
import com.mixienixie.ebookstore.service.EntityMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper used for bi directional mapping between CreateReviewsRequest object and ReviewsEntity object
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ReviewsCreateMapper extends EntityMapper<CreateReviewsRequest, ReviewsEntity> {

    @Mapping(source = "bookId", target = "book.id")
    ReviewsEntity toEntity(CreateReviewsRequest reviewsRequest);
}
