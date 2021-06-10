package com.mixienixie.ebookstore.repo.core.entity;

import lombok.Data;

/**
 * DTO class used for Author view
 *
 * @author mdjukanovic
 */
@Data
public class AuthorDto {

    /** Author id **/
    private Long id;

    /** First name **/
    private String firstName;

    /** Last name **/
    private String lastName;

    /** Birth Year */
    private Integer birthYear;

    /** (Description)About **/
    private String about;
}
