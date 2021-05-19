package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.repo.core.entity.CategoryDto;
import com.mixienixie.ebookstore.repo.core.entity.CategoryEntity;
import com.mixienixie.ebookstore.service.EntityMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * Mapper used for bi directional mapping between CategoryDto object and CategoryEntity object
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CategoryViewMapper extends EntityMapper<CategoryDto, CategoryEntity> {
}
