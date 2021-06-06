package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreateBookManagementRequestsRequest;
import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;
import com.mixienixie.ebookstore.repo.core.BookManagementRequestsRepository;
import com.mixienixie.ebookstore.repo.core.entity.*;
import com.mixienixie.ebookstore.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
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

    private final PublishingHouseService publishingHouseService;

    /** Book Create Mapper **/
    private final BookViewMapper bookViewMapper;

    /** Book service **/
    private final BookService bookService;

    /** Authorization service **/
    private final AuthorizationService authorizationService;

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
        PublishingHouseEntity publishingHouse = this.getPublishingHouseForAuthorizedUser();

        Page<BookManagementRequestsDto> bookManagementRequestsDtos = this.bookManagementRequestsRepository.
                findByProcessedAndPublishingHouse(processed, publishingHouse, pageable).
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

    @Override
    public BookManagementRequestsDto approveBookManagementRequest(Long requestId, CreateBookManagementRequestsRequest bookManagementRequestsRequest) {
        Objects.requireNonNull(requestId);
        Objects.requireNonNull(bookManagementRequestsRequest);
        Objects.requireNonNull(bookManagementRequestsRequest.getBookId());

        BookDto bookDto = this.bookService.findByBookId(bookManagementRequestsRequest.getBookId());

        int newQuantity = bookDto.getInStock() + bookManagementRequestsRequest.getQuantity();
        if(newQuantity < 0) {
            throw new ValidationException("Book request cannot be approved, book quantity would be less then zero");
        }

        bookDto.setInStock(newQuantity);
        this.bookService.save(this.bookViewMapper.toEntity(bookDto));

        BookManagementRequestsEntity bookManagementRequestsEntity = this.bookManagementRequestsCreateMapper.toEntity(bookManagementRequestsRequest);
        bookManagementRequestsEntity.setId(requestId);
        bookManagementRequestsEntity.setProcessed(true);
        bookManagementRequestsEntity.setProcessedByUserId(this.authorizationService.getAuthenticatedUser().getId());
        bookManagementRequestsEntity = this.bookManagementRequestsRepository.save(bookManagementRequestsEntity);

        return this.bookManagementRequestsViewMapper.toDto(bookManagementRequestsEntity);
    }

    private void fillWithBooks(Page<BookManagementRequestsDto> bookManagementRequestsDtos) {
        bookManagementRequestsDtos.forEach((bookManagementRequestsDto) -> {
            bookManagementRequestsDto.setBook(this.fillBookDto(bookManagementRequestsDto.getBook()));
        });
    }

    private BookDto fillBookDto(BookDto book) {
        return this.bookService.findByBookId(book.getId());
    }

    private PublishingHouseEntity getPublishingHouseForAuthorizedUser(){
        UserEntity userEntity = this.authorizationService.getAuthenticatedUser();

        String tin = this.roleService.getPublishingHouseTinForPublishingHouseRepresentative(userEntity.getId());
        return this.publishingHouseService.findByTin(tin);
    }
}
