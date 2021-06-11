package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreateOrderItemRequest;
import com.mixienixie.ebookstore.core.requests.CreateOrderRequest;
import com.mixienixie.ebookstore.repo.core.BookRepository;
import com.mixienixie.ebookstore.repo.core.OrderItemRepository;
import com.mixienixie.ebookstore.repo.core.OrderRepository;
import com.mixienixie.ebookstore.repo.core.entity.BookEntity;
import com.mixienixie.ebookstore.repo.core.entity.OrderDto;
import com.mixienixie.ebookstore.repo.core.entity.OrderEntity;
import com.mixienixie.ebookstore.repo.core.entity.OrderItemEntity;
import com.mixienixie.ebookstore.service.AuthorizationService;
import com.mixienixie.ebookstore.service.NotificationService;
import com.mixienixie.ebookstore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 *
 * Default implementation of the Order Service
 * @author mdjukanovic
 */
@Service
@AllArgsConstructor
@Transactional
public class DefaultOrderService implements OrderService {

    /** Order Repository */
    private final OrderRepository orderRepository;
    /** Order Item Repository */
    private final OrderItemRepository orderItemRepository;
    /** Book Repository */
    private final BookRepository bookRepository;

    /** Authorization Service */
    private final AuthorizationService authorizationService;
    /** Notification Service */
    private final NotificationService notificationService;

    /** Order View Mapper */
    private final OrderCreateMapper orderCreateMapper;
    /** Order Create Mapper */
    private final OrderViewMapper orderViewMapper;
    /** Order Item Create Mapper */
    private final OrderItemCreateMapper orderItemCreateMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDto create(CreateOrderRequest createOrderRequest) {
        Objects.requireNonNull(createOrderRequest);
        Objects.requireNonNull(createOrderRequest.getOrderItems());

        Set<OrderItemEntity> orderItemEntities = this.createOrderItemEntities(createOrderRequest.getOrderItems());
        OrderEntity orderEntity = this.prepareOrder(createOrderRequest, orderItemEntities);

        orderEntity = this.orderRepository.save(orderEntity);

        this.notificationService.sendBookPurchaseNotification(orderEntity);

        return this.orderViewMapper.toDto(orderEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<OrderDto> findAll(Pageable pageable) {
        Objects.requireNonNull(pageable);

        return this.orderRepository.findAll(pageable).map(this.orderViewMapper::toDto);
    }

    /**
     * Creates the Order Item Entities for the passed set of Create Order Item Requests
     * @param orderItemRequests Set of create order item requests to create
     * @return Set of Order Item Entities
     */
    private Set<OrderItemEntity> createOrderItemEntities(Set<CreateOrderItemRequest> orderItemRequests){
        Set<OrderItemEntity> orderItemEntities = new HashSet<>();
        orderItemRequests.forEach(createOrderItemRequest -> {
            OrderItemEntity orderItemEntity = this.orderItemCreateMapper.toEntity(createOrderItemRequest);

            Long bookId = orderItemEntity.getBook().getId();
            BookEntity bookEntity = this.bookRepository.findById(bookId)
                    .orElseThrow(() -> new EntityNotFoundException("Non existing book, passed for order, with id: " + bookId));

            bookEntity.setInStock(bookEntity.getInStock() - orderItemEntity.getQuantity());
            if(bookEntity.getInStock() < 0){
                throw new ValidationException("Not enough books in stock for book: " + bookEntity.getTitle() + " (id: " + bookId + ")");
            }
            bookEntity.setSold(bookEntity.getSold() + orderItemEntity.getQuantity());
            orderItemEntity.setBook(this.bookRepository.save(bookEntity));

            orderItemEntity = this.orderItemRepository.save(orderItemEntity);
            orderItemEntities.add(orderItemEntity);
        });

        return orderItemEntities;
    }

    /**
     * Generates the Order Entity from the CreateOrder request and sets the mandatory properties
     * @param createOrderRequest createOrderRequest
     * @param orderItemEntities Set of Order Item Entities for this order
     * @return
     */
    private OrderEntity prepareOrder(CreateOrderRequest createOrderRequest, Set<OrderItemEntity> orderItemEntities){
        OrderEntity orderEntity = this.orderCreateMapper.toEntity(createOrderRequest);
        orderEntity.setOrderItems(orderItemEntities);
        orderEntity.setOrderDate(new Date());
        orderEntity.setUserId(this.authorizationService.getAuthenticatedUser().getId());

        return orderEntity;
    }
}
