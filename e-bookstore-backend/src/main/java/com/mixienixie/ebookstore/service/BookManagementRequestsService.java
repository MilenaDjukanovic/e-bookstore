package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.core.requests.CreateBookManagementRequestsRequest;
import com.mixienixie.ebookstore.repo.core.entity.BookManagementRequestsDto;

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
}
