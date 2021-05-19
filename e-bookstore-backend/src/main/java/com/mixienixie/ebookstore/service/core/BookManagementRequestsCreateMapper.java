package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreateBookManagementRequestsRequest;
import com.mixienixie.ebookstore.repo.core.entity.BookManagementRequestsEntity;
import com.mixienixie.ebookstore.service.EntityMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper used for bi directional mapping between CreateBookManagementRequestsRequest
 * object and BookManagementRequestsEntity object
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface BookManagementRequestsCreateMapper extends EntityMapper<CreateBookManagementRequestsRequest, BookManagementRequestsEntity> {

    @Mapping(source = "publishingHouseId", target = "publishingHouse.id")
    BookManagementRequestsEntity toEntity(CreateBookManagementRequestsRequest bookManagementRequestsRequest);

}
