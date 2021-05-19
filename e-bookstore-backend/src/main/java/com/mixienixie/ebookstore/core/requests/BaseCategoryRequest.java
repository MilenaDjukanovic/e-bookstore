package com.mixienixie.ebookstore.core.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Base class for Book requests
 *
 * @author mdjukanovic
 */
@Data
public abstract class BaseCategoryRequest {

    /** Name **/
    @NotBlank
    private String name;
}
