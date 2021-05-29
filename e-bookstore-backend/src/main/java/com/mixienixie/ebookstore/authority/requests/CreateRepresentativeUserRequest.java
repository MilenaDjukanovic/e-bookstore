package com.mixienixie.ebookstore.authority.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * Create Publishing House Representative User
 *
 * @author ndjordjieski
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateRepresentativeUserRequest extends CreateUserRequest{

    /** Publishing Hosue Representative Registration Key */
    @NotBlank
    private String representativeRegistrationKey;
}
