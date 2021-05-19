package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.repo.core.entity.OrderDto;
import com.mixienixie.ebookstore.repo.core.entity.OrderEntity;
import com.mixienixie.ebookstore.service.EntityMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * Mapper used for bi directional mapping between OrderDto object and OrderEntity object
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface OrderViewMapper extends EntityMapper<OrderDto, OrderEntity> {
}
