package com.mixienixie.ebookstore.repo.core;

import com.mixienixie.ebookstore.repo.core.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * OrderEntity repository
 *
 * @author mdjukanovic
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    /**
     * Finds orders by the id of user who made them
     * @param userId id of the user
     * @param pageable pageable
     * @return pageable of orders
     */
    Page<OrderEntity> findOrderEntitiesByUserId(Long userId, Pageable pageable);

    /**
     * Finds orders by the date
     * @param orderDate date of the order
     * @param pageable pageable
     * @return pageable of orders
     */
    Page<OrderEntity> findOrderEntitiesByOrderDate(Date orderDate, Pageable pageable);

//    Page<OrderEntity> findOrderEntitiesByBooks_(Set<BookEntity> books, Pageable pageable);

}
