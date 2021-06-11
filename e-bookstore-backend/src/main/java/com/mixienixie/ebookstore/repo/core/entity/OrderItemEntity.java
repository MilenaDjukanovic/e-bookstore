package com.mixienixie.ebookstore.repo.core.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Order Item Entity
 * @author ndjordjieski
 */
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "order_item")
@ToString @EqualsAndHashCode(onlyExplicitlyIncluded = false)
public class OrderItemEntity{

    /** Order Item Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    /** Book */
    @OneToOne()
    @Getter @Setter
    private BookEntity book;

    /** Quantity */
    @Column
    @Getter @Setter
    private Integer quantity;
}
