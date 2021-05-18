package com.mixienixie.ebookstore.authority.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * Create User request
 *
 * @author ndjordjieski
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateUserRequest extends BaseUserRequest{

    /** Repeated Password (for confirmation) */
    @NotBlank
    private String rePassword;
    /** First Name */
    @NotBlank
    private String firstName;
    /** Last Name */
    @NotBlank
    private String lastName;
}
