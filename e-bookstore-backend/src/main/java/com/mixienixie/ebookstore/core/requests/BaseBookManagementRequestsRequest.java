package com.mixienixie.ebookstore.core.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Base class for Book management  requests related requests
 *
 * @author mdjukanovic
 */
@Data
public abstract class BaseBookManagementRequestsRequest {

    /** Book id **/
    @NotNull
    private Long bookId;

    /** Quantity **/
    private int quantity;

    /** Reason why the the change was requested **/
    private String reason;
}
