package com.mixienixie.ebookstore.rest.core.controller;

import com.mixienixie.ebookstore.core.requests.CreateOrderRequest;
import com.mixienixie.ebookstore.repo.core.entity.OrderDto;
import com.mixienixie.ebookstore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("api/public")
@AllArgsConstructor()
public class OrderController {

    private final OrderService orderService;

    @GetMapping("orders")
    public Page<OrderDto> findAll(Pageable pageable) {
        return this.orderService.findAll(pageable);
    }

    @PostMapping("orders")
    public OrderDto create(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        return this.orderService.create(createOrderRequest);
    }
}
