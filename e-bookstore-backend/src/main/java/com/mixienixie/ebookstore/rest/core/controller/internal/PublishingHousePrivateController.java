package com.mixienixie.ebookstore.rest.core.controller.internal;

import com.mixienixie.ebookstore.repo.core.entity.BookDto;
import com.mixienixie.ebookstore.security.authorizations.IsPublishingHouseRepresentative;
import com.mixienixie.ebookstore.service.PublishingHouseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Private API Endpoint for Publishing Houses
 * @author ndjordjieski
 */
@RestController() @RequestMapping("api/private/publishing-houses")
@IsPublishingHouseRepresentative()
@AllArgsConstructor
public class PublishingHousePrivateController{

    /** Publishing House Service */
    private final PublishingHouseService publishingHouseService;

    @GetMapping("books")
    public Page<BookDto> findAllBooksForPublishingHouse(){
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        return this.publishingHouseService.findAllBooksForPublishingHouse(pageable);
    }
}
