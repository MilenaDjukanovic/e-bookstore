package com.mixienixie.ebookstore.core.requests;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Base class for Book requests
 *
 * @author mdjukanovic
 */
@Data
public abstract class BaseBookRequest {

    /** Title **/
    @NotBlank
    private String title;

    /** Description **/
    @NotBlank
    private String description;

    /** Image(Cover) **/
    @NotNull
    private byte[] image;

    /** Price **/
    @NotNull @Min(value = 1)
    private double price;

    /** Number of books in stock **/
    @NotNull
    private int inStock;

    /** Author **/
    @NotNull
    private Long authorId;

    /** Category **/
    @NotNull
    private Long categoryId;

    /** Publishing house **/
    @NotNull
    private Long publishingHouseId;

}
