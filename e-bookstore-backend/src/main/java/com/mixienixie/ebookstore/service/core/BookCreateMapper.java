package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreateBookRequest;
import com.mixienixie.ebookstore.repo.core.entity.BookEntity;
import com.mixienixie.ebookstore.service.EntityMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper used for bi directional mapping between CreateBookRequest object and BookEntity object
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface BookCreateMapper extends EntityMapper<CreateBookRequest, BookEntity> {

    @Mapping(source = "authorId", target = "author.id")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "publishingHouseId", target = "publishingHouse.id")
    BookEntity toEntity(CreateBookRequest bookRequest);
}
