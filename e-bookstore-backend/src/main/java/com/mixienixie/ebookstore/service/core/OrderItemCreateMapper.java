package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreateOrderItemRequest;
import com.mixienixie.ebookstore.repo.core.entity.OrderItemEntity;
import com.mixienixie.ebookstore.service.EntityMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper used for bi directional mapping between CreateOrderItemRequest object and OrderItemEntity object
 *
 * @author ndjordjieski
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface OrderItemCreateMapper extends EntityMapper<CreateOrderItemRequest, OrderItemEntity>{

    @Mapping(source = "bookId", target = "book.id")
    OrderItemEntity toEntity(CreateOrderItemRequest createOrderItemRequest);
}
