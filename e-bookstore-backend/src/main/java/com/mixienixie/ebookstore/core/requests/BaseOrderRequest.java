package com.mixienixie.ebookstore.core.requests;

import lombok.Data;

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

    /** Id of the user who made the order **/
    @NotNull
    private Long userId;

    /** Date **/
    @NotNull
    private Date orderDate;

    /** Books that were ordered **/
    @NotNull
    private Set<Long> bookIds;
}
