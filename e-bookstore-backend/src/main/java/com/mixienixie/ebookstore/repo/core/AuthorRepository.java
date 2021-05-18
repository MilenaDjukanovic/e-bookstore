package com.mixienixie.ebookstore.repo.core;

import com.mixienixie.ebookstore.repo.core.entity.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AuthorEntity repository
 *
 * @author mdjukanovic
 */
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    /**
     * Finds authors by first name
     * @param firstName first name of the author
     * @param pageable pageable
     * @return pageable of authors
     */
    Page<AuthorEntity> findAuthorEntitiesByFirstName(String firstName, Pageable pageable);

    /**
     * Finds authors by last name
     * @param lastName last name of the author
     * @param pageable pageable
     * @return pageable of authors
     */
    Page<AuthorEntity> findAuthorEntitiesByLastName(String lastName, Pageable pageable);

    /**
     * Finds authors by first and last name
     * @param firstName first name of the author
     * @param lastName last name of the author
     * @param pageable pageable
     * @return pageable of authors
     */
    Page<AuthorEntity> findAuthorEntitiesByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);
}
