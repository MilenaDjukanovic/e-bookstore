package com.mixienixie.ebookstore.repo.core;

import com.mixienixie.ebookstore.repo.core.entity.BookEntity;
import com.mixienixie.ebookstore.repo.core.entity.CategoryEntity;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
     * Finds books that belong to the passed publishing house
     * @param publishingHouseEntity Publishing House to find books for
     * @return pageable of books
     */
    Page<BookEntity> findBookEntitiesByPublishingHouse(PublishingHouseEntity publishingHouseEntity, Pageable pageable);


    /**
     * Finds book by its id
     * @param bookId id of the book
     * @return optional of Book Entity
     */
    Optional<BookEntity> findById(Long bookId);

    /**
     * Deletes book by its id
     * @param id id of the book
     */
    void deleteById(Long id);

    /**
     * Finds book by its title and category
     * @param title title of the Book
     * @param categoryEntity category of the book
     * @param pageable pageable
     * @return pageable of Books
     */
    Page<BookEntity> findBookEntitiesByTitleAndCategory(String title, CategoryEntity categoryEntity, Pageable pageable);

    /**
     * Finds book by its category
     * @param categoryEntity category of the book
     * @param pageable pageable
     * @return pageable of books
     */
    Page<BookEntity> findBookEntitiesByCategory(CategoryEntity categoryEntity, Pageable pageable);

}
