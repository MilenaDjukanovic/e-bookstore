package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreateBookManagementRequestsRequest;
import com.mixienixie.ebookstore.repo.core.BookManagementRequestsRepository;
import com.mixienixie.ebookstore.repo.core.entity.BookManagementRequestsDto;
import com.mixienixie.ebookstore.repo.core.entity.BookManagementRequestsEntity;
import com.mixienixie.ebookstore.service.BookManagementRequestsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public BookManagementRequestsDto create(CreateBookManagementRequestsRequest bookManagementRequestsRequest) {
        BookManagementRequestsEntity bookManagementRequestsEntity = this.bookManagementRequestsCreateMapper.toEntity(bookManagementRequestsRequest);

        bookManagementRequestsEntity = this.bookManagementRequestsRepository.save(bookManagementRequestsEntity);

        return this.bookManagementRequestsViewMapper.toDto(bookManagementRequestsEntity);
    }

    @Override
    public Page<BookManagementRequestsDto> findAll(Pageable pageable) {
        Objects.requireNonNull(pageable);

        return this.bookManagementRequestsRepository.findAll(pageable).
                map(this.bookManagementRequestsViewMapper::toDto);
    }
}
