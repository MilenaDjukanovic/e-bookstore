package com.mixienixie.ebookstore.repo.core.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "author")
public class AuthorEntity {

    /** Author id **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    /** First name **/
    @Column(name = "first_name", nullable = false)
    @Getter @Setter
    private String firstName;

    /** Last name **/
    @Column(name = "last_name", nullable = false)
    @Getter @Setter
    private String lastName;

    /** Birth Year */
    @Column(name = "birth_year", nullable = false)
    @Getter @Setter
    private Integer birthYear;

    /** (Description)About **/
    @Column
    @Getter @Setter
    private String about;
}
