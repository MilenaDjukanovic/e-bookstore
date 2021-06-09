package com.mixienixie.ebookstore.rest.core.controller.internal;

import com.mixienixie.ebookstore.core.requests.CreateCategoryRequest;
import com.mixienixie.ebookstore.repo.core.entity.CategoryDto;
import com.mixienixie.ebookstore.security.authorizations.IsPublishingHouseRepresentative;
import com.mixienixie.ebookstore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Private API Endpoint for Categories
 * @author ndjordjieski
 */
@RestController @RequestMapping("api/private/categories")
@IsPublishingHouseRepresentative()
@AllArgsConstructor
public class CategoryPrivateController{

    /** Category Service */
    private final CategoryService categoryService;

    @PostMapping()
    public CategoryDto create(@RequestBody @Valid CreateCategoryRequest createCategoryRequest){
        return this.categoryService.create(createCategoryRequest);
    }
}
