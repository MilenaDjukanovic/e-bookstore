package com.mixienixie.ebookstore.rest.core.controller;

import com.mixienixie.ebookstore.core.requests.CreateCategoryRequest;
import com.mixienixie.ebookstore.repo.core.entity.CategoryDto;
import com.mixienixie.ebookstore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("api/public")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("categories")
    public Page<CategoryDto> findAll(Pageable pageable) {
        return this.categoryService.findAll(pageable);
    }

    @PostMapping("categories")
    public CategoryDto create(@RequestBody @Valid CreateCategoryRequest createCategoryRequest){
        return this.categoryService.create(createCategoryRequest);
    }
}
