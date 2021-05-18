package com.mixienixie.ebookstore.repo.core.entity;

import lombok.Data;

/**
 * DTO class used for Reviews view
 *
 * @author mdjukanovic
 */
@Data
public class ReviewsDto {

    /** Review id **/
    private Long reviewsId;

    /** Book that was reviewed **/
    private BookDto book;

    /** Id of the user who made the review **/
    private Long userId;

    /** Comment **/
    private String comment;

    /** Grade **/
    private int grade;

}
