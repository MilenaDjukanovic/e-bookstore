package com.mixienixie.ebookstore.repo.core.entity;

import lombok.Data;

/**
 * Order Item DTO
 * @author ndjordjieski
 */
@Data
public class OrderItemDto{

    /** Order Item Id */
    private Long id;
    /** Book */
    private BookDto book;
    /** Quantity */
    private Integer quantity;
}
