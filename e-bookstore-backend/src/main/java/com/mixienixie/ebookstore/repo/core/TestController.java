package com.mixienixie.ebookstore.repo.core;

import com.mixienixie.ebookstore.core.requests.*;
import com.mixienixie.ebookstore.repo.core.entity.BookEntity;
import com.mixienixie.ebookstore.service.*;
import com.mixienixie.ebookstore.service.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/public/test")
public class TestController {

    @Autowired
    private PublishingHouseService publishingHouseService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookManagementRequestsService bookManagementRequestsService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String test() {
        CreateAuthorRequest createAuthorRequest = new CreateAuthorRequest();
        createAuthorRequest.setFirstName("Miggie");
        createAuthorRequest.setLastName("Miggie");
        createAuthorRequest.setAbout("Miggi je super");
        this.authorService.create(createAuthorRequest);
//
//        CreatePublishingHouseRequest createPublishingHouseRequest = new CreatePublishingHouseRequest();
//        createPublishingHouseRequest.setEmail("miggie@niggie");
//        createPublishingHouseRequest.setCompanyName("Miggzeli");
//        createPublishingHouseRequest.setRepresentativeRegistrationKey("123");
//        createPublishingHouseRequest.setTin("123");
//        this.publishingHouseService.create(createPublishingHouseRequest);
//
        CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest();
        createCategoryRequest.setName("novo");
        this.categoryService.create(createCategoryRequest);

        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setTitle("Sidarta");
        createBookRequest.setPublishingHouseId(1L);
        createBookRequest.setInStock(43);
        createBookRequest.setPrice(66.4);
        createBookRequest.setDescription("prelijepa knjiga");
        String smt = "dhjas";
        createBookRequest.setImage(smt);
        createBookRequest.setAuthorId(1L);
        createBookRequest.setCategoryId(1L);
        this.bookService.create(createBookRequest);

//        CreateBookManagementRequestsRequest createBookManagementRequestsRequest = new CreateBookManagementRequestsRequest();
//        createBookManagementRequestsRequest.setBookId(1L);
//        createBookManagementRequestsRequest.setQuantity(23);
//        createBookManagementRequestsRequest.setReason("Nije li ovo divan dan");
//        this.bookManagementRequestsService.create(createBookManagementRequestsRequest);
//
//        CreateBookManagementRequestsRequest createBookManagementRequestsRequest2 = new CreateBookManagementRequestsRequest();
//        createBookManagementRequestsRequest2.setBookId(1L);
//        createBookManagementRequestsRequest2.setQuantity(55);
//        createBookManagementRequestsRequest2.setReason("Why not");
//        this.bookManagementRequestsService.create(createBookManagementRequestsRequest2);

        return "222";
    }
}
