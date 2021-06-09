package com.mixienixie.ebookstore.rest.core.controller.external;

import com.mixienixie.ebookstore.repo.core.entity.CategoryDto;
import com.mixienixie.ebookstore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Public API Endpoint for Categories
 * @author ndjordjieski
 */
@RestController @RequestMapping("api/public/categories")
@AllArgsConstructor
public class CategoryPublicController{

    /** Category Service */
    private final CategoryService categoryService;

    @GetMapping()
    public Page<CategoryDto> findAll(Pageable pageable) {
        return this.categoryService.findAll(pageable);
    }

    @GetMapping("all")
    public Page<CategoryDto> findAllNoLimit(){
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        return this.categoryService.findAll(pageable);
    }
}
