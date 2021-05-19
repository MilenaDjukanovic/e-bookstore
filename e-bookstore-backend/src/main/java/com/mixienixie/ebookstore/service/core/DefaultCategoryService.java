package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreateCategoryRequest;
import com.mixienixie.ebookstore.repo.core.CategoryRepository;
import com.mixienixie.ebookstore.repo.core.entity.CategoryDto;
import com.mixienixie.ebookstore.repo.core.entity.CategoryEntity;
import com.mixienixie.ebookstore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 *
 * Default implementation of the Category Service
 * @author mdjukanovic
 */
@Service
@AllArgsConstructor
public class DefaultCategoryService implements CategoryService {

    /** Category Repository */
    private final CategoryRepository categoryRepository;

    /** Category View Mapper */
    private final CategoryCreateMapper categoryCreateMapper;

    /** Category Create Mapper */
    private final CategoryViewMapper categoryViewMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoryDto create(CreateCategoryRequest createCategoryRequest) {
        CategoryEntity categoryEntity = this.categoryCreateMapper.toEntity(createCategoryRequest);

        categoryEntity = this.categoryRepository.save(categoryEntity);

        return this.categoryViewMapper.toDto(categoryEntity);
    }
}
