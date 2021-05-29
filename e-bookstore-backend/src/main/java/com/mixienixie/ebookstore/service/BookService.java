package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.core.requests.CreateBookRequest;
import com.mixienixie.ebookstore.repo.core.entity.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    Page<BookDto> findAll(Pageable pageable);
}
