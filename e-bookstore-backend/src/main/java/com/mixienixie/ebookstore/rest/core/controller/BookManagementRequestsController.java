package com.mixienixie.ebookstore.rest.core.controller;

import com.mixienixie.ebookstore.core.requests.CreateBookManagementRequestsRequest;
import com.mixienixie.ebookstore.repo.core.entity.BookManagementRequestsDto;
import com.mixienixie.ebookstore.service.BookManagementRequestsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("api/public/book-management-requests")
@AllArgsConstructor
public class BookManagementRequestsController {

    private final BookManagementRequestsService bookManagementRequestsService;

    @GetMapping("all")
    public Page<BookManagementRequestsDto> findAll(Pageable pageable) {
        return this.bookManagementRequestsService.findAll(pageable);
    }

    @GetMapping("pending")
    public Page<BookManagementRequestsDto> findByProcessed
            (@RequestParam boolean processed, Pageable pageable){
        return this.bookManagementRequestsService.findAllByProcessed(processed, pageable);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        this.bookManagementRequestsService.deleteBookManagementRequestById(id);
    }

    @GetMapping()
    public Page<BookManagementRequestsDto> findByProcessedAndByPublishingHouse
            (@RequestParam Long id, @RequestParam boolean processed, Pageable pageable){
        return this.bookManagementRequestsService.findAllByProcessedAndByPublishingHouse(processed, id, pageable);
    }

    @PostMapping("create")
    public BookManagementRequestsDto create
            (@RequestBody @Valid CreateBookManagementRequestsRequest createBookManagementRequestsRequest){
        return this.bookManagementRequestsService.create(createBookManagementRequestsRequest);
    }

    @PutMapping("approve/{id}")
    public BookManagementRequestsDto approveBookManagementRequest(@PathVariable Long id){
        return this.bookManagementRequestsService.approveBookManagementRequest(id);
    }
}
