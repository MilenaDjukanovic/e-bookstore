package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.core.requests.CreatePublishingHouseRequest;
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
     * Finds the publishing house based on the passed representative registration key
     * @param representativeRegistrationKey representativeRegistrationKey of the publishing house
     * @return PublishingHouse object for representativeRegistrationKey
     */
    PublishingHouseEntity findPublishingHouseEntityByRepresentativeRegistrationKey(String representativeRegistrationKey);
}
