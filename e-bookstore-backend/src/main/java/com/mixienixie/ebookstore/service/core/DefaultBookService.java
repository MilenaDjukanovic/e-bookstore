package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreateBookRequest;
import com.mixienixie.ebookstore.repo.core.BookRepository;
import com.mixienixie.ebookstore.repo.core.entity.BookDto;
import com.mixienixie.ebookstore.repo.core.entity.BookEntity;
import com.mixienixie.ebookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 *
 * Default implementation of the Book Service
 * @author mdjukanovic
 */
@Service
@AllArgsConstructor
public class DefaultBookService implements BookService {

    /** Book Repository */
    private final BookRepository bookRepository;

    /** Book View Mapper */
    private final BookCreateMapper bookCreateMapper;

    /** Book Create Mapper */
    private final BookViewMapper bookViewMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public BookDto create(CreateBookRequest createBookRequest) {
        BookEntity bookEntity = this.bookCreateMapper.toEntity(createBookRequest);

        bookEntity = this.bookRepository.save(bookEntity);

        return this.bookViewMapper.toDto(bookEntity);
    }
}
