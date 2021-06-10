package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.core.requests.CreatePublishingHouseRequest;
import com.mixienixie.ebookstore.repo.core.entity.AuthorDto;
import com.mixienixie.ebookstore.repo.core.entity.BookDto;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseDto;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for Publishing house related operations
 *
 * @author mdjukanovic
 */
public interface PublishingHouseService {

    /**
     * Creates publishing house from the createPublishingHouseRequest
     * @param createPublishingHouseRequest  request for publishing house creation containing publishing house details
     * @return PublishingHouse object for viewing if successful
     */
    PublishingHouseDto create(CreatePublishingHouseRequest createPublishingHouseRequest);

    /**
     * Finds all publishing houses based on passed Pageable object
     * @param pageable Pageable
     * @return Pageable of publishing houses
     */
    Page<PublishingHouseDto> findAll(Pageable pageable);

    /**
     * Finds the Publishing House based on the passed id
     * @param id id of publishing house to find
     * @return PublishingHouseEntity for passed id
     */
    PublishingHouseEntity findById(Long id);

    /**
     * Finds the publishing house based on the passed representative registration key
     * @param representativeRegistrationKey representativeRegistrationKey of the publishing house
     * @return PublishingHouseEntity for representativeRegistrationKey
     */
    PublishingHouseEntity findPublishingHouseEntityByRepresentativeRegistrationKey(String representativeRegistrationKey);

    /**
     * Finds the publishing house based on the passed TIN
     * @param tin publishing house TIN
     * @return PublishingHouseEntity for TIN
     */
    PublishingHouseEntity findByTin(String tin);

    /**
     * Finds all books for the publishing house of the currently logged in user
     * @param pageable
     * @return
     */
    Page<BookDto> findAllBooksForPublishingHouse(Pageable pageable);

    /**
     * Deletes book
     * @param bookId
     */
    void deleteBook(Long bookId);
}
