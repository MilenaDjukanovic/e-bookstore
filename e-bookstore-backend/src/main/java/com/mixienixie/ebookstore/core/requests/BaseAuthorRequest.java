package com.mixienixie.ebookstore.core.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Base class for Author related requests
 *
 * @author mdjukanovic
 */
@Data
public abstract class BaseAuthorRequest {

    /** First name **/
    @NotBlank
    private String firstName;

    /** Last name **/
    @NotBlank
    private String lastName;

    /** (Description)About **/
    private String about;
}
