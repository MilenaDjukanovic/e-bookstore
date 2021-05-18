package com.mixienixie.ebookstore.authority.requests;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Base class for User related requests
 *
 * @author ndjordjieski
 */
@Data
public abstract class BaseUserRequest{

    /** Username */
    @NotBlank @Email
    private String username;
    /** Password */
    @NotBlank
    private String password;
}
