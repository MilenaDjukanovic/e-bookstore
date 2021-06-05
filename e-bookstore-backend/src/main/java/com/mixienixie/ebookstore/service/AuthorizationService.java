package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.repo.authority.entity.RoleEntity;
import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;

import java.util.Set;

/**
 * Service for handling Authorization related operations
 * @author ndjordjieski
 */
public interface AuthorizationService{

    /**
     * Retrieves the currently authenticated user (user that is performing requests)
     * @return UserEntity for authenticated user
     */
    UserEntity getAuthenticatedUser();

    /**
     * Retrieves the authorities of the currently authenticated user
     * @return Set of RoleEntities for authenticated user
     */
    Set<RoleEntity> getAuthorizedUserRoles();
}
