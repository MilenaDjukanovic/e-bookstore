package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.core.requests.CreateBookManagementRequestsRequest;
import com.mixienixie.ebookstore.repo.core.BookManagementRequestsRepository;
import com.mixienixie.ebookstore.repo.core.entity.BookManagementRequestsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for BookManagementRequests related operations
 *
 * @author mdjukanovic
 */
public interface BookManagementRequestsService {

    /**
     * Creates book management request from the createBookManagementRequestsRequest
     * @param bookManagementRequestsRequest request for book management requests creation containing book management requests details
     * @return Book management requests object for viewing if successful
     */
    BookManagementRequestsDto create(CreateBookManagementRequestsRequest bookManagementRequestsRequest);

    Page<BookManagementRequestsDto> findAll(Pageable pageable);

    Page<BookManagementRequestsDto> findAllByProcessedAndByPublishingHouse(boolean processed, Long userId, Pageable pageable);

    Page<BookManagementRequestsDto> findAllByProcessed(boolean processed, Pageable pageable);

    void deleteBookManagementRequestById(Long id);

    BookManagementRequestsDto approveBookManagementRequest(Long id, CreateBookManagementRequestsRequest bookManagementRequestsRequest);
}
