package com.mixienixie.ebookstore.repo.core;

import com.mixienixie.ebookstore.repo.core.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * BookEntity repository
 *
 * @author mdjukanovic
 */
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    /**
     * Finds books by title
     * @param title title of the book
     * @param pageable pageable
     * @return pageable of books
     */
    Page<BookEntity> findBookEntitiesByTitle(String title, Pageable pageable);

    /**
     * Finds book with the price greater then
     * @param price price
     * @param pageable pageable
     * @return pageable of books
     */
    Page<BookEntity> findBookEntitiesByPriceGreaterThan(double price, Pageable pageable);

    /**
     * Finds book with the price less then
     * @param price price
     * @param pageable pageable
     * @return pageable of books
     */
    Page<BookEntity> findBookEntitiesByPriceLessThan(double price, Pageable pageable);


    /**
     * Finds book by its id
     * @param bookId id of the book
     * @return optional of Book Entity
     */
    Optional<BookEntity> findById(Long bookId);
}
