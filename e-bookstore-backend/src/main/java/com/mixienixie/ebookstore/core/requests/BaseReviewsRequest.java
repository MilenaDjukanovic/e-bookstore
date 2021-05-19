package com.mixienixie.ebookstore.core.requests;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Base class for Review requests
 *
 * @author mdjukanovic
 */
@Data
public abstract class BaseReviewsRequest {

    /** Id of the book that was reviewed **/
    @NotNull
    private Long bookId;

    /** Id of the user who made the review **/
    @NotNull
    private Long userId;

    /** Comment **/
    @NotNull
    private String comment;

    /** Grade **/
    @NotNull @Min(value = 0)
    private int grade;
}
