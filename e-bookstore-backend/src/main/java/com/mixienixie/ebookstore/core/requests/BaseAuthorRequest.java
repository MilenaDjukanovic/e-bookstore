package com.mixienixie.ebookstore.core.requests;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotNull @Min(0)
    private Integer birthYear;

    /** (Description)About **/
    private String about;
}
