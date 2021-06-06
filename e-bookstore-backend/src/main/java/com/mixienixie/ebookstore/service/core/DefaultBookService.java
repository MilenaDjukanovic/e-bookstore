package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreateBookRequest;
import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;
import com.mixienixie.ebookstore.repo.core.BookRepository;
import com.mixienixie.ebookstore.repo.core.entity.BookDto;
import com.mixienixie.ebookstore.repo.core.entity.BookEntity;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseEntity;
import com.mixienixie.ebookstore.service.AuthorizationService;
import com.mixienixie.ebookstore.service.BookService;
import com.mixienixie.ebookstore.service.PublishingHouseService;
import com.mixienixie.ebookstore.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

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

    /** Authorization Service */
    private final AuthorizationService authorizationService;

    /** Publishing House Service */
    private final PublishingHouseService publishingHouseService;

    /** Role Service */
    private final RoleService roleService;

    /**
     * {@inheritDoc}
     */
    @Override
    public BookDto create(CreateBookRequest createBookRequest) {
        UserEntity userEntity = this.authorizationService.getAuthenticatedUser();
        PublishingHouseEntity publishingHouse = this.publishingHouseService
                .findByTin(this.roleService.getPublishingHouseTinForPublishingHouseRepresentative(userEntity.getId()));
        createBookRequest.setPublishingHouseId(publishingHouse.getId());

        BookEntity bookEntity = this.bookCreateMapper.toEntity(createBookRequest);

        bookEntity = this.bookRepository.save(bookEntity);

        return this.bookViewMapper.toDto(bookEntity);
    }

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        Objects.requireNonNull(pageable);

        return this.bookRepository.findAll(pageable).map(this.bookViewMapper::toDto);
    }

    @Override
    public BookDto findByBookId(Long id) {
        BookEntity bookEntity = this.bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id: " + id + " not found"));
        return this.bookViewMapper.toDto(bookEntity);
    }

    @Override
    public BookDto save(BookEntity bookEntity) {
        bookEntity = this.bookRepository.save(bookEntity);

        return bookViewMapper.toDto(bookEntity);
    }
}
