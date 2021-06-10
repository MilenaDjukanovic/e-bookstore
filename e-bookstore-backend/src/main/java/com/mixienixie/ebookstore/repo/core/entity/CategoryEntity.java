package com.mixienixie.ebookstore.repo.core.entity;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "categories")
public class CategoryEntity {

    /** Category id **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    /** Name **/
    @Column(nullable = false, unique = true)
    @Getter @Setter
    private String name;
}
