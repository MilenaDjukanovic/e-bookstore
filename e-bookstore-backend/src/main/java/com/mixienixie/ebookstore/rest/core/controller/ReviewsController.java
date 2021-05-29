package com.mixienixie.ebookstore.rest.core.controller;

import com.mixienixie.ebookstore.core.requests.CreateReviewsRequest;
import com.mixienixie.ebookstore.repo.core.entity.ReviewsDto;
import com.mixienixie.ebookstore.service.ReviewsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("api/public")
@AllArgsConstructor
public class ReviewsController {

    private final ReviewsService reviewsService;

    @GetMapping("reviews")
    public Page<ReviewsDto> findAll(Pageable pageable) {
        return this.reviewsService.findAll(pageable);
    }

    @PostMapping("reviews")
    public ReviewsDto create(@RequestBody @Valid CreateReviewsRequest createReviewsRequest) {
        return this.reviewsService.create(createReviewsRequest);
    }
}
