package com.mixienixie.ebookstore.repo.core;

import com.mixienixie.ebookstore.repo.core.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Order Item Repository
 * @author ndjordjieski
 */
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long>{

}
