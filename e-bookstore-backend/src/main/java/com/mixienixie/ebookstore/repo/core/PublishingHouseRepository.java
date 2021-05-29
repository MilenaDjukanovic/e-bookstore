package com.mixienixie.ebookstore.repo.core;

import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * PublishingHouseEntity repository
 *
 * @author mdjukanovic
 */
public interface PublishingHouseRepository extends JpaRepository<PublishingHouseEntity, Long> {

    /**
     * Finds publishing houses by the company name
     * @param companyName name of the publishing house
     * @param pageable pageable
     * @return pageable of publishing houses
     */
    Page<PublishingHouseEntity> findPublishingHouseEntitiesByCompanyName(String companyName, Pageable pageable);

    /**
     * Finds publishing house by email
     * @param email email of the publishing house
     * @return optional publishing house
     */
    Optional<PublishingHouseEntity> findPublishingHouseEntityByEmail(String email);

    /**
     * Finds publishing house by TIN
     * @param tin TIN of the publishing house
     * @return optional publishing house
     */
    Optional<PublishingHouseEntity> findPublishingHouseEntityByTin(String tin);

    /**
     * Finds the publishing house by their Representative Registration Key
     * @param representativeRegistrationKey representative registration key
     * @return optional publishing house
     */
    Optional<PublishingHouseEntity> findPublishingHouseEntityByRepresentativeRegistrationKey(String representativeRegistrationKey);
}
