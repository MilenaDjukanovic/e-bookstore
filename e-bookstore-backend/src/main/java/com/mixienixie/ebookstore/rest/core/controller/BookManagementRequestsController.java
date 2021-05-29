package com.mixienixie.ebookstore.rest.core.controller;

import com.mixienixie.ebookstore.core.requests.CreateBookManagementRequestsRequest;
import com.mixienixie.ebookstore.repo.core.entity.BookManagementRequestsDto;
import com.mixienixie.ebookstore.service.BookManagementRequestsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("api/public")
@AllArgsConstructor
public class BookManagementRequestsController {

    private final BookManagementRequestsService bookManagementRequestsService;

    @GetMapping("book-management-requests")
    public Page<BookManagementRequestsDto> findAll(Pageable pageable) {
        return this.bookManagementRequestsService.findAll(pageable);
    }

    @PostMapping("book-management-requests")
    public BookManagementRequestsDto create(@RequestBody @Valid CreateBookManagementRequestsRequest createBookManagementRequestsRequest){
        return this.bookManagementRequestsService.create(createBookManagementRequestsRequest);
    }
}
