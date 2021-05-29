package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.core.requests.CreateOrderRequest;
import com.mixienixie.ebookstore.repo.core.entity.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for Order related operations
 *
 * @author mdjukanovic
 */
public interface OrderService {

    /**
     * Creates order from the createOrderRequest
     * @param createOrderRequest  request for order creation containing order details
     * @return Order object for viewing if successful
     */
    OrderDto create(CreateOrderRequest createOrderRequest);

    Page<OrderDto> findAll(Pageable pageable);
}
