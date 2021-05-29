package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.authority.requests.CreateRepresentativeUserRequest;
import com.mixienixie.ebookstore.authority.requests.CreateUserRequest;
import com.mixienixie.ebookstore.repo.authority.entity.UserDto;

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
    UserDto createUser(CreateUserRequest createUserRequest);

    /**
     * Creates the representative user from the createRepresentativeUserRequest
     * @param createRepresentativeUserRequest request for representative user creation containing user details
     * @return User object for viewer if successful
     */
    UserDto createRepresentativeUser(CreateRepresentativeUserRequest createRepresentativeUserRequest);
}
