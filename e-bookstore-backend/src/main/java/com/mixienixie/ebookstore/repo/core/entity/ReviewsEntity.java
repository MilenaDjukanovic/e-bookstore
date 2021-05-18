package com.mixienixie.ebookstore.repo.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "reviews")
public class ReviewsEntity implements Serializable {

    /** Review id **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name = "id")
    private Long reviewId;

    /** Book that was reviewed **/
    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    /** Id of the user who made the review **/
    @Getter @Setter
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** Comment **/
    @Column(nullable = false)
    @Getter @Setter
    private String comment;

    /** Grade **/
    @Column
    @Getter @Setter
    private int grade;
}
