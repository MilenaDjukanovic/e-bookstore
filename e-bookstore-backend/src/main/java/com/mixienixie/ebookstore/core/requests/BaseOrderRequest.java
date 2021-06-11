package com.mixienixie.ebookstore.core.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * Base class for Order requests
 *
 * @author mdjukanovic
 */
@Data
public abstract class BaseOrderRequest {

    /** Books that were ordered **/
    @NotNull
    private Set<CreateOrderItemRequest> orderItems;

    /** Address */
    @NotBlank
    private String address;
}
