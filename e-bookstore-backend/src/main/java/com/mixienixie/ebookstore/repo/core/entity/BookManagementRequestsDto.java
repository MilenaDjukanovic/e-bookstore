package com.mixienixie.ebookstore.repo.core.entity;

import lombok.Data;

/**
 * DTO class used for BookManagementView view
 *
 * @author mdjukanovic
 */
@Data
public class BookManagementRequestsDto {

    /** Book management id **/
    private Long id;

    /** Publishing house requesting book stocks change **/
    private PublishingHouseDto publishingHouse;

    /** Id of the book referenced by the request **/
    private Long bookId;

    /** Required quantity **/
    private int quantity;

    /** Was request processed **/
    private boolean processed;

    /** Id of the user who processed the request **/
    private Long processedByUserId;

    /** Reason why the the change was requested **/
    private String reason;
}
