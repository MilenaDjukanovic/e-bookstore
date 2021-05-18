package com.mixienixie.ebookstore.repo.core.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "books")
public class BookEntity {

    /** Book id **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    /** Title **/
    @Column(nullable = false)
    @Getter @Setter
    private String title;

    /** Description **/
    @Column(nullable = false)
    @Getter @Setter
    private String description;

    /** Image(cover) **/
    @Lob
    @Column(nullable = false, columnDefinition = "BLOB")
    @Getter @Setter
    private byte[] image;

    /** Price **/
    @Column(nullable = false)
    @Getter @Setter
    private double price;

    /** Number of books in stock **/
    @Column(name = "in_stock")
    @Getter @Setter
    private int inStock;

    /** Sold **/
    @Column
    @Getter @Setter
    private int sold;

    /** Author **/
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @Getter @Setter
    private AuthorEntity author;

    /** Category **/
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @Getter @Setter
    private CategoryEntity category;

    /** Publishing house **/
    @ManyToOne
    @JoinColumn(name = "publishing_house_id", nullable = false)
    @Getter @Setter
    private PublishingHouseEntity publishingHouse;
}
