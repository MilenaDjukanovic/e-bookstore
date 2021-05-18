package com.mixienixie.ebookstore.repo.core.entity;

import lombok.Data;

/**
 * DTO class used for Category view
 *
 * @author mdjukanovic
 */
@Data
public class CategoryDto {

    /** Category id **/
    private Long id;

    /** Name **/
    private String name;
}
