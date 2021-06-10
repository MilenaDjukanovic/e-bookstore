package com.mixienixie.ebookstore.rest.core.controller.internal;

import com.mixienixie.ebookstore.core.requests.CreateBookManagementRequestsRequest;
import com.mixienixie.ebookstore.repo.core.entity.BookManagementRequestsDto;
import com.mixienixie.ebookstore.security.authorizations.IsAdminUser;
import com.mixienixie.ebookstore.security.authorizations.IsPublishingHouseRepresentative;
import com.mixienixie.ebookstore.service.BookManagementRequestsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Private API Endpoint for Categories
 * @author ndjordjieski
 */
@RestController @RequestMapping("api/private/book-management-requests")
@IsPublishingHouseRepresentative
@AllArgsConstructor
public class BookManagementRequestsPrivateController{

    /** Book Management Requests Service */
    private final BookManagementRequestsService bookManagementRequestsService;

    @IsAdminUser()
    @GetMapping("all")
    public Page<BookManagementRequestsDto> findAll(Pageable pageable) {
        return this.bookManagementRequestsService.findAll(pageable);
    }

    @IsAdminUser
    @GetMapping("pending")
    public Page<BookManagementRequestsDto> findByProcessed
            (@RequestParam boolean processed, Pageable pageable){
        return this.bookManagementRequestsService.findAllByProcessed(processed, pageable);
    }

    @IsAdminUser
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

    @IsAdminUser
    @PutMapping("approve/{id}")
    public BookManagementRequestsDto approveBookManagementRequest(@PathVariable Long id){
        return this.bookManagementRequestsService.approveBookManagementRequest(id);
    }
}
