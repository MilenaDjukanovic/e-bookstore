package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.core.requests.CreateAuthorRequest;
import com.mixienixie.ebookstore.repo.core.entity.AuthorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for Author related operations
 *
 * @author mdjukanovic
 */
public interface AuthorService {

    /**
     * Creates author from the createBookRequest
     * @param createAuthorRequest  request for author creation containing author details
     * @return Author object for viewing if successful
     */
    AuthorDto create(CreateAuthorRequest createAuthorRequest);

    Page<AuthorDto> findAll(Pageable pageable);
}
