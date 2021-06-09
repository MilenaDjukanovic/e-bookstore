package com.mixienixie.ebookstore.rest.core.controller.internal;

import com.mixienixie.ebookstore.core.requests.CreateBookRequest;
import com.mixienixie.ebookstore.repo.core.entity.BookDto;
import com.mixienixie.ebookstore.security.authorizations.IsPublishingHouseRepresentative;
import com.mixienixie.ebookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Private API Endpoint for Books
 * @author ndjordjieski
 */
@RestController @RequestMapping("api/private/books")
@IsPublishingHouseRepresentative()
@AllArgsConstructor
public class BookPrivateController{

    /** Book Service */
    private final BookService bookService;

    @PostMapping()
    public BookDto create(@RequestBody @Valid CreateBookRequest createBookRequest) {
        return this.bookService.create(createBookRequest);
    }
}
