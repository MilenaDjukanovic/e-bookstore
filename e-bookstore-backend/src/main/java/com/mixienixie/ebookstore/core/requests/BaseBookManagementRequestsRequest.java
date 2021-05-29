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

    /** Publishing house id **/
    @NotNull
    private Long publishingHouseId;

    /** Book id **/
    @NotNull
    private Long bookId;

    /** Quantity **/
    private int quantity;

    /** Id of the user who processed the request **/
    @NotNull
    private Long processedByUserId;

    /** Reason why the the change was requested **/
    private String reason;
}