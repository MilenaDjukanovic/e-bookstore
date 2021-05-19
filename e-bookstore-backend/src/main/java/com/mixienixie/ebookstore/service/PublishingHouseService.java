package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.core.requests.CreatePublishingHouseRequest;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseDto;

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
}
