package com.mixienixie.ebookstore.rest.core.controller;

import com.mixienixie.ebookstore.core.requests.CreatePublishingHouseRequest;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseDto;
import com.mixienixie.ebookstore.service.PublishingHouseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("api/public")
@AllArgsConstructor
public class PublishingHouseController {

    private final PublishingHouseService publishingHouseService;

    @GetMapping("publishing-houses")
    public Page<PublishingHouseDto> findAll(Pageable pageable) {
        return this.publishingHouseService.findAll(pageable);
    }

    @PostMapping("publishing-houses")
    public PublishingHouseDto create(@RequestBody @Valid CreatePublishingHouseRequest createPublishingHouseRequest) {
        return this.publishingHouseService.create(createPublishingHouseRequest);
    }
}
