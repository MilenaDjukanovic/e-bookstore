package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.core.requests.CreatePublishingHouseRequest;
import com.mixienixie.ebookstore.repo.core.PublishingHouseRepository;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseDto;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseEntity;
import com.mixienixie.ebookstore.service.PublishingHouseService;
import com.mixienixie.ebookstore.service.RoleService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
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

    /** Role Service */
    private final RoleService roleService;

    /**
     * {@inheritDoc}
     */
    @Override
    public PublishingHouseDto create(CreatePublishingHouseRequest createPublishingHouseRequest) {
        PublishingHouseEntity publishingHouseEntity = this.publishingHouseCreateMapper.toEntity(createPublishingHouseRequest);

        publishingHouseEntity = this.publishingHouseRepository.save(publishingHouseEntity);

        // For each publishing house, we create a role for their representatives
        this.roleService.createRepresentativeRoleForPublishingHouse(publishingHouseEntity);

        return this.publishingHouseViewMapper.toDto(publishingHouseEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PublishingHouseDto> findAll(Pageable pageable) {
        Objects.requireNonNull(pageable);

        return this.publishingHouseRepository.findAll(pageable).map(this.publishingHouseViewMapper::toDto);
    }

    /**
     * Finds the publishing house based on the passed representative registration key
     *
     * @param representativeRegistrationKey representativeRegistrationKey of the publishing house
     * @return PublishingHouse object for representativeRegistrationKey
     */
    @Override
    public PublishingHouseEntity findPublishingHouseEntityByRepresentativeRegistrationKey(String representativeRegistrationKey){
        if(StringUtils.isEmpty(representativeRegistrationKey)){
            throw new ValidationException("Representative Registration Key must not be null or empty!");
        }

        return this.publishingHouseRepository.findPublishingHouseEntityByRepresentativeRegistrationKey(representativeRegistrationKey)
                .orElseThrow(() -> new EntityNotFoundException("Could not find publishing house for provided representative " +
                        "registration key"));
    }
}
