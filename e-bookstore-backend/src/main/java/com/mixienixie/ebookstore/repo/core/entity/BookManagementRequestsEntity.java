package com.mixienixie.ebookstore.repo.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "book_management_requests")
public class BookManagementRequestsEntity implements Serializable {

    /** Book management request id **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private Long id;

    /** Publishing house requesting book stocks change **/
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "publishing_house_id", nullable = false)
    private PublishingHouseEntity publishingHouse;

    /** Id of the book referenced by the request **/
    @Column(name = "book_id", nullable = false)
    @Getter @Setter
    private Long bookId;

    /** Required quantity **/
    @Column
    @Getter @Setter
    private int quantity;

    /** Was request processed **/
    @Column
    @Getter @Setter
    private boolean processed = false;

    /** Id of the user who processed the request **/
    @Column(name = "processed_by_user_id")
    @Getter @Setter
    private Long processedByUserId;

    /** Reason why the the change was requested **/
    @Column
    @Getter @Setter
    private String reason;
}
