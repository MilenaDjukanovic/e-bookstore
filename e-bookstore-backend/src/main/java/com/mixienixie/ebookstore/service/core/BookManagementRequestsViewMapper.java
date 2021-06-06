package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.repo.core.entity.BookManagementRequestsDto;
import com.mixienixie.ebookstore.repo.core.entity.BookManagementRequestsEntity;
import com.mixienixie.ebookstore.service.EntityMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper used for bi directional mapping between BookManagementRequestsDto object and BookManagementRequestsEntity object
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface BookManagementRequestsViewMapper extends EntityMapper<BookManagementRequestsDto, BookManagementRequestsEntity> {

    @Mapping(source = "bookId", target = "book.id")
    BookManagementRequestsDto toDto(BookManagementRequestsEntity bookManagementRequestsEntity);

}
