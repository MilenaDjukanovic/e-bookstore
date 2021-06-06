package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.core.requests.CreateBookRequest;
import com.mixienixie.ebookstore.repo.core.entity.BookDto;
import com.mixienixie.ebookstore.repo.core.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service for Book related operations
 *
 * @author mdjukanovic
 */
public interface BookService {

    /**
     * Creates book from the createBookRequest
     * @param createBookRequest  request for book creation containing book details
     * @return Book object for viewing if successful
     */
    BookDto create(CreateBookRequest createBookRequest);

    /**
     * Finds all books
     * @param pageable pageable
     * @return pageable of books
     */
    Page<BookDto> findAll(Pageable pageable);

    /**
     * Finds book by id
     * @param id id of book
     * @return optional of BookDto
     */
    BookDto findByBookId(Long id);
}
