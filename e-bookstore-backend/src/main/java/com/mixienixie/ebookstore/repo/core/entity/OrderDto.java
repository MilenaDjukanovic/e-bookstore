package com.mixienixie.ebookstore.repo.core.entity;

import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * DTO class used for Order view
 *
 * @author mdjukanovic
 */
@Data
public class OrderDto {

    /** Order id **/
    private Long id;

    /** Id of the user who made the order **/
    private Long userId;

    /** Date **/
    private Date orderDate;

    /** Books that were ordered **/
    private Set<BookDto> books;
}
