package com.mixienixie.ebookstore.repo.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "book_orders")
public class OrderEntity implements Serializable {

    /** Order id **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    /** Id of the user who made the order **/
    @Getter @Setter
    @Column(name = "user_id")
    private Long userId;

    /** Date **/
    @Column(name = "order_date", nullable = false)
    @Getter @Setter
    private Date orderDate;

    /** Order Items **/
    @OneToMany
    @Getter @Setter
    private Set<OrderItemEntity> orderItems = new HashSet<>();

    @Column
    @Getter @Setter
    private String address;
}
