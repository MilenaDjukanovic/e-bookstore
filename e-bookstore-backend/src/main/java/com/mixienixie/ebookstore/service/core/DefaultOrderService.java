package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreateOrderRequest;
import com.mixienixie.ebookstore.repo.core.OrderRepository;
import com.mixienixie.ebookstore.repo.core.entity.OrderDto;
import com.mixienixie.ebookstore.repo.core.entity.OrderEntity;
import com.mixienixie.ebookstore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * {@inheritDoc}
 *
 * Default implementation of the Order Service
 * @author mdjukanovic
 */
@Service
@AllArgsConstructor
public class DefaultOrderService implements OrderService {

    /** Order Repository */
    private final OrderRepository orderRepository;

    /** Order View Mapper */
    private final OrderCreateMapper orderCreateMapper;

    /** Order Create Mapper */
    private final OrderViewMapper orderViewMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDto create(CreateOrderRequest createOrderRequest) {
        OrderEntity orderEntity = this.orderCreateMapper.toEntity(createOrderRequest);

        orderEntity = this.orderRepository.save(orderEntity);

        return this.orderViewMapper.toDto(orderEntity);
    }

    @Override
    public Page<OrderDto> findAll(Pageable pageable) {
        Objects.requireNonNull(pageable);

        return this.orderRepository.findAll(pageable).map(this.orderViewMapper::toDto);
    }
}
