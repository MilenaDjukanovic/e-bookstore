package com.mixienixie.ebookstore.repo.authority.entity;

import lombok.Data;

import java.util.Set;

/**
 * DTO class used for User view
 *
 * @author ndjordjieski
 */
@Data
public class UserDto{

    /** User Id */
    private String id;

    /** Username */
    private String username;
    /** First Name */
    private String firstName;
    /** Last name */
    private String lastName;

    /** User Roles */
    private Set<RoleEntity> authorities;
}
