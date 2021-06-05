package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.authority.requests.CreateRepresentativeUserRequest;
import com.mixienixie.ebookstore.authority.requests.CreateUserRequest;
import com.mixienixie.ebookstore.repo.authority.entity.UserDto;
import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;

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
     * Updates the passed userEntity
     * @param userEntity userEntity to update
     * @return Updated UserEntity
     */
    UserEntity save(UserEntity userEntity);

    /**
     * Creates the representative user from the createRepresentativeUserRequest
     * @param createRepresentativeUserRequest request for representative user creation containing user details
     * @return User object for viewer if successful
     */
    UserDto createRepresentativeUser(CreateRepresentativeUserRequest createRepresentativeUserRequest);

    /**
     * Finds the User for the passed id
     * @param id id of user to find
     * @return UserEntity for passed id
     */
    UserEntity findById(Long id);
}
