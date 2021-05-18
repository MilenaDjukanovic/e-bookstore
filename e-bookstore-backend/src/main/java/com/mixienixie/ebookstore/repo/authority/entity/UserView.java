package com.mixienixie.ebookstore.repo.authority.entity;

import lombok.Data;

/**
 * DTO class used for User view
 *
 * @author ndjordjieski
 */
@Data
public class UserView{

    /** User Id */
    private String id;

    /** Username */
    private String username;
    /** First Name */
    private String firstName;
    /** Last name */
    private String lastName;
}
