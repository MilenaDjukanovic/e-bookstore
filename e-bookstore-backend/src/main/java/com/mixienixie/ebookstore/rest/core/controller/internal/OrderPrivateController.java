package com.mixienixie.ebookstore.rest.core.controller.internal;

import com.mixienixie.ebookstore.core.requests.CreateOrderRequest;
import com.mixienixie.ebookstore.repo.core.entity.OrderDto;
import com.mixienixie.ebookstore.security.authorizations.IsUser;
import com.mixienixie.ebookstore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Private API for Orders
 * @author ndjordjieski
 */
@RestController @RequestMapping("api/private/orders")
@AllArgsConstructor
@Transactional
@IsUser
public class OrderPrivateController{

    /** Order Service */
    private final OrderService orderService;

    @PostMapping()
    public OrderDto create(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        return this.orderService.create(createOrderRequest);
    }
}
