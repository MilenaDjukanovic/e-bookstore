package com.mixienixie.ebookstore.repo.core;

import com.mixienixie.ebookstore.repo.core.entity.BookManagementRequestsEntity;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BookManagementRequestsEntity repository
 *
 * @author mdjukanovic
 */
public interface BookManagementRequestsRepository extends JpaRepository<BookManagementRequestsEntity, Long> {

    /**
     * Finds books management requests by book id
     * @param bookId book id
     * @param pageable pageable
     * @return pageable of book management requests
     */
    Page<BookManagementRequestsEntity> findBookManagementRequestsEntitiesByBookId(Long bookId, Pageable pageable);

    /**
     * Finds book management by publishing house
     * @param publishingHouse publishing house
     * @param pageable pageable
     * @return pageable of book management requests
     */
    Page<BookManagementRequestsEntity> findBookManagementRequestsEntitiesByPublishingHouse(PublishingHouseEntity publishingHouse, Pageable pageable);

    /**
     * Finds book management by process state
     * @param processed is request processed
     * @param pageable pageable
     * @return pageable of book management requests
     */
    Page<BookManagementRequestsEntity> findBookManagementRequestsEntitiesByProcessed(boolean processed, Pageable pageable);

    /**
     * Finds book management by user who processed it
     * @param processedByUserId id of the user who processed the request
     * @param pageable pageable
     * @return pageable of book management requests
     */
    Page<BookManagementRequestsEntity> findBookManagementRequestsEntitiesByProcessedByUserId(Long processedByUserId, Pageable pageable);

    /**
     * Finds book management by user who processed it and by processed attribute
     * @param publishingHouseEntity id of the user who processed the request
     * @param processed finds processed or pending book management requests
     * @param pageable pageable
     * @return pageable of book management requests
     */
    Page<BookManagementRequestsEntity> findByProcessedAndPublishingHouse(boolean processed,PublishingHouseEntity publishingHouseEntity, Pageable pageable);

    void deleteById(Long id);
}
