package com.mixienixie.ebookstore.rest.core.controller.external;

import com.mixienixie.ebookstore.core.requests.CreatePublishingHouseRequest;
import com.mixienixie.ebookstore.repo.core.entity.AuthorDto;
import com.mixienixie.ebookstore.repo.core.entity.BookDto;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseDto;
import com.mixienixie.ebookstore.service.AuthorService;
import com.mixienixie.ebookstore.service.PublishingHouseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Public API Endpoint for Publishing Houses
 * @author ndjordjieski
 */
@RestController @RequestMapping("api/public/publishing-houses")
@AllArgsConstructor
public class PublishingHousePublicController{

    /** Publishing House Service */
    private final PublishingHouseService publishingHouseService;

    @GetMapping()
    public Page<PublishingHouseDto> findAll(Pageable pageable){
        return this.publishingHouseService.findAll(pageable);
    }

    @PostMapping("create")
    public PublishingHouseDto create(@RequestBody @Valid CreatePublishingHouseRequest createPublishingHouseRequest){
        return this.publishingHouseService.create(createPublishingHouseRequest);
    }
}
