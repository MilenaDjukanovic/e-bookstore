package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.repo.core.entity.ReviewsDto;
import com.mixienixie.ebookstore.repo.core.entity.ReviewsEntity;
import com.mixienixie.ebookstore.service.EntityMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * Mapper used for bi directional mapping between ReviewsDto object and ReviewsEntity object
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ReviewsViewMapper extends EntityMapper<ReviewsDto, ReviewsEntity> {
}
