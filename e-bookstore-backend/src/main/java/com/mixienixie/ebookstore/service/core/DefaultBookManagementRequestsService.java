package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreateBookManagementRequestsRequest;
import com.mixienixie.ebookstore.repo.core.BookManagementRequestsRepository;
import com.mixienixie.ebookstore.repo.core.PublishingHouseRepository;
import com.mixienixie.ebookstore.repo.core.entity.BookDto;
import com.mixienixie.ebookstore.repo.core.entity.BookManagementRequestsDto;
import com.mixienixie.ebookstore.repo.core.entity.BookManagementRequestsEntity;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseEntity;
import com.mixienixie.ebookstore.service.BookManagementRequestsService;
import com.mixienixie.ebookstore.service.BookService;
import com.mixienixie.ebookstore.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Objects;

/**
 * {@inheritDoc}
 *
 * Default implementation of the Book Management Request Service
 * @author mdjukanovic
 */
@Service
@AllArgsConstructor
public class DefaultBookManagementRequestsService implements BookManagementRequestsService {

    /** Book management requests Repository */
    private final BookManagementRequestsRepository bookManagementRequestsRepository;

    /** Book management requests View Mapper */
    private final BookManagementRequestsCreateMapper bookManagementRequestsCreateMapper;

    /** Book management requests Create Mapper */
    private final BookManagementRequestsViewMapper bookManagementRequestsViewMapper;

    private final PublishingHouseRepository publishingHouseRepository;

    /** Book service **/
    private final BookService bookService;

    /** Role service **/
    private final RoleService roleService;

    /**
     * {@inheritDoc}
     */
    @Override
    public BookManagementRequestsDto create(CreateBookManagementRequestsRequest bookManagementRequestsRequest) {
        BookManagementRequestsEntity bookManagementRequestsEntity = this.bookManagementRequestsCreateMapper.toEntity(bookManagementRequestsRequest);

        bookManagementRequestsEntity = this.bookManagementRequestsRepository.save(bookManagementRequestsEntity);

        BookManagementRequestsDto bookManagementRequestsDto =
                this.bookManagementRequestsViewMapper.toDto(bookManagementRequestsEntity);
        this.fillBookDto(bookManagementRequestsDto.getBook());

        return bookManagementRequestsDto;
    }

    @Override
    public Page<BookManagementRequestsDto> findAll(Pageable pageable) {
        Objects.requireNonNull(pageable);

        Page<BookManagementRequestsDto> bookManagementRequestsDtos = this.bookManagementRequestsRepository.findAll(pageable).
                map(this.bookManagementRequestsViewMapper::toDto);
        this.fillWithBooks(bookManagementRequestsDtos);

        return bookManagementRequestsDtos;
    }

    @Override
    public Page<BookManagementRequestsDto> findAllByProcessedAndByPublishingHouse(boolean processed, Long userId, Pageable pageable) {
        PublishingHouseEntity publishingHouseEntity = this.publishingHouseRepository.findAll(pageable).get().findFirst().orElse(null);
        Page<BookManagementRequestsDto> bookManagementRequestsDtos = this.bookManagementRequestsRepository.
                findByProcessedAndPublishingHouse(processed, publishingHouseEntity, pageable).
                map(this.bookManagementRequestsViewMapper::toDto);
        this.fillWithBooks(bookManagementRequestsDtos);

        return bookManagementRequestsDtos;
    }

    @Override
    public Page<BookManagementRequestsDto> findAllByProcessed(boolean processed, Pageable pageable) {
        Page<BookManagementRequestsDto> bookManagementRequestsDtos = this.bookManagementRequestsRepository.
                findBookManagementRequestsEntitiesByProcessed(processed, pageable).
                map(this.bookManagementRequestsViewMapper::toDto);
        this.fillWithBooks(bookManagementRequestsDtos);

        return bookManagementRequestsDtos;
    }

    @Override
    public void deleteBookManagementRequestById(Long id) {
        this.bookManagementRequestsRepository.deleteById(id);
    }

    private void fillWithBooks(Page<BookManagementRequestsDto> bookManagementRequestsDtos) {
        bookManagementRequestsDtos.forEach((bookManagementRequestsDto) -> {
            bookManagementRequestsDto.setBook(this.fillBookDto(bookManagementRequestsDto.getBook()));
        });
    }

    private BookDto fillBookDto(BookDto book) {
        return this.bookService.findByBookId(book.getId());
    }
}
