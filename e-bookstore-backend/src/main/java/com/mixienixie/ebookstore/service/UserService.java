package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.authority.requests.CreateUserRequest;
import com.mixienixie.ebookstore.repo.authority.entity.UserView;

/**
 * Service for User related operations
 *
 * @author ndjordjieski
 */
public interface UserService{

    /**
     * Creates the user from the createUserRequest
     * @param createUserRequest request for user creation containing user details
     * @return User object for viewing if successful
     */
    UserView create(CreateUserRequest createUserRequest);
}
