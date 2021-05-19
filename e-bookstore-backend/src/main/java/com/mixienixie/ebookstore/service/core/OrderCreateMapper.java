package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreateOrderRequest;
import com.mixienixie.ebookstore.repo.core.entity.BookEntity;
import com.mixienixie.ebookstore.repo.core.entity.OrderEntity;
import com.mixienixie.ebookstore.service.EntityMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper used for bi directional mapping between CreateOrderRequest object and OrderEntity object
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface OrderCreateMapper extends EntityMapper<CreateOrderRequest, OrderEntity> {

    @Mapping(source = "bookIds", target = "books")
    OrderEntity toEntity(CreateOrderRequest orderRequest);

    default BookEntity mapToBookId(Long id) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(id);
        return bookEntity;
    }
}
