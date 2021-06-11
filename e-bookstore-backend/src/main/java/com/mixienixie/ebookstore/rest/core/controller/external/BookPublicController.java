package com.mixienixie.ebookstore.rest.core.controller.external;

import com.mixienixie.ebookstore.core.requests.CreateBookRequest;
import com.mixienixie.ebookstore.repo.core.entity.BookDto;
import com.mixienixie.ebookstore.search.SearchBook;
import com.mixienixie.ebookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
