package com.mixienixie.ebookstore.rest.core.controller.external;

import com.mixienixie.ebookstore.repo.core.entity.BookDto;
import com.mixienixie.ebookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Public API Endpoint for Books
 * @author ndjordjieski
 */
@RestController @RequestMapping("api/public/books")
@AllArgsConstructor
public class BookPublicController{

    /** Book Service */
    private final BookService bookService;

    @GetMapping()
    public Page<BookDto> findAll(Pageable pageable) {
        return this.bookService.findAll(pageable);
    }
}
