package com.mixienixie.ebookstore.rest.core.controller.external;

import com.mixienixie.ebookstore.core.requests.CreateOrderRequest;
import com.mixienixie.ebookstore.repo.core.entity.OrderDto;
import com.mixienixie.ebookstore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Public API Endpoint for orders
 * @author ndjordjieski
 */
@RestController
@RequestMapping("api/public/orders")
@AllArgsConstructor()
public class OrderPublicController{
    private final OrderService orderService;

    @GetMapping()
    public Page<OrderDto> findAll(Pageable pageable) {
        return this.orderService.findAll(pageable);
    }

    @PostMapping()
    public OrderDto create(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        return this.orderService.create(createOrderRequest);
    }
}
