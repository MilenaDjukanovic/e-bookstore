package com.mixienixie.ebookstore.core.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Base class for Order Item requests
 * @author ndjordjieski
 */
@Data
@EqualsAndHashCode()
public class BaseOrderItemRequest{

    /** Book Id */
    private Long bookId;

    /** Quantity */
    private Integer quantity;
}
