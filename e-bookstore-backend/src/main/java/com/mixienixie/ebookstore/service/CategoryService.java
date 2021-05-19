package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.core.requests.CreateCategoryRequest;
import com.mixienixie.ebookstore.repo.core.entity.CategoryDto;

/**
 * Service for Category related operations
 *
 * @author mdjukanovic
 */
public interface CategoryService {

    /**
     * Creates order from the createCategoryRequest
     * @param createCategoryRequest  request for category creation containing category details
     * @return Category object for viewing if successful
     */
    CategoryDto create(CreateCategoryRequest createCategoryRequest);
}
