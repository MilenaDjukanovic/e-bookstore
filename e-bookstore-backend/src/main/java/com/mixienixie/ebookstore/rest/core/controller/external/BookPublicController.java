package com.mixienixie.ebookstore.rest.core.controller.external;

import com.mixienixie.ebookstore.repo.core.entity.BookDto;
import com.mixienixie.ebookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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

    @GetMapping("search")
    public Page<BookDto> searchBooks(@RequestParam() Optional<String> title,
                                     @RequestParam() Optional<Long> categoryId, Pageable pageable){
        return this.bookService.searchBooksByTitleAndCategory(title, categoryId, pageable);
    }

    //    @GetMapping()
//    public List<BookDto> search(@ModelAttribute SearchBook searchBook, Pageable pageable){
//        return this.bookService.searchBooks(searchBook, pageable);
//    }
//
}
