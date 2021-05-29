package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreatePublishingHouseRequest;
import com.mixienixie.ebookstore.repo.core.PublishingHouseRepository;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseDto;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseEntity;
import com.mixienixie.ebookstore.service.PublishingHouseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * {@inheritDoc}
 *
 * Default implementation of the Publishing house Service
 * @author mdjukanovic
 */
@Service
@AllArgsConstructor
public class DefaultPublishingHouseService implements PublishingHouseService {

    /** Publishing house Repository */
    private final PublishingHouseRepository publishingHouseRepository;

    /** Publishing house View Mapper */
    private final PublishingHouseCreateMapper publishingHouseCreateMapper;

    /** Publishing Create Mapper */
    private final PublishingHouseViewMapper publishingHouseViewMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public PublishingHouseDto create(CreatePublishingHouseRequest createPublishingHouseRequest) {
        PublishingHouseEntity publishingHouseEntity = this.publishingHouseCreateMapper.toEntity(createPublishingHouseRequest);

        publishingHouseEntity = this.publishingHouseRepository.save(publishingHouseEntity);

        return this.publishingHouseViewMapper.toDto(publishingHouseEntity);
    }

    @Override
    public Page<PublishingHouseDto> findAll(Pageable pageable) {
        Objects.requireNonNull(pageable);

        return this.publishingHouseRepository.findAll(pageable).map(this.publishingHouseViewMapper::toDto);
    }
}
