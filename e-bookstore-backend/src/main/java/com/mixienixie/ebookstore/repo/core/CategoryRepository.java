package com.mixienixie.ebookstore.repo.core;

import com.mixienixie.ebookstore.repo.core.entity.CategoryDto;
import com.mixienixie.ebookstore.repo.core.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * CategoryEntity repository
 *
 * @author mdjukanovic
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    /**
     * Finds categories by name
     * @param name name of the category
     * @param pageable pageable
     * @return pageable of categories
     */
    Page<CategoryEntity> findCategoryEntitiesByName(String name, Pageable pageable);

    Optional<CategoryEntity> findById(Long id);
}
