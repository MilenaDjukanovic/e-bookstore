package com.mixienixie.ebookstore.repo.core.entity;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "publishing_houses")
public class PublishingHouseEntity {

    /** Publishing house id **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    /** Company name **/
    @Column(name = "company_name", nullable = false)
    @Getter @Setter
    private String companyName;

    /** Email **/
    @Column(nullable = false)
    @Getter @Setter
    private String email;

    /** TIN **/
    @Column(nullable = false)
    @Getter @Setter
    private String TIN;

}
