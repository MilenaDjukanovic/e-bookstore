package com.mixienixie.ebookstore.repo.core.entity;

import lombok.Data;

/**
 * DTO class used for Book view
 *
 * @author mdjukanovic
 */
@Data
public class BookDto {

    /** Book id **/
    private Long id;

    /** Title **/
    private String title;

    /** Description **/
    private String description;

    /** Image(cover) **/
    private String image;

    /** Price **/
    private double price;

    /** Number of books in stock **/
    private int inStock;

    /** Sold **/
    private int sold;

    /** Author **/
    private AuthorDto author;

    /** Category **/
    private CategoryDto category;

    /** Publishing house **/
    private PublishingHouseDto publishingHouse;
}
