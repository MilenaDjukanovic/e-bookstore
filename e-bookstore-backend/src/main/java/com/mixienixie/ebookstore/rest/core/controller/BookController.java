package com.mixienixie.ebookstore.rest.core.controller;

import com.mixienixie.ebookstore.core.requests.CreateBookRequest;
import com.mixienixie.ebookstore.repo.core.entity.BookDto;
import com.mixienixie.ebookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("api/public")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("books")
    public Page<BookDto> findAll(Pageable pageable) {
        return this.bookService.findAll(pageable);
    }

    @GetMapping("books/all")
    public Page<BookDto> findAll(){
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        return this.bookService.findAll(pageable);
    }

    @PostMapping("books")
    public BookDto create(@RequestBody @Valid CreateBookRequest createBookRequest) {
        return this.bookService.create(createBookRequest);
    }
}
