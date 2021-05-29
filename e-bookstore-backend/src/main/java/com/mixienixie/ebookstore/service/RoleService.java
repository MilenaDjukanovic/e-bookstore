package com.mixienixie.ebookstore.service;

import com.mixienixie.ebookstore.repo.authority.entity.RoleEntity;
import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseEntity;

/**
 * Service for Role related operations
 * @author ndjordjieski
 */
public interface RoleService{

    /** User Role **/
    String ROLE_USER = "USER";
    /** Admin Role **/
    String ROLE_ADMIN = "ADMIN";
    /** Publisher Representative Role */
    String ROLE_PUBLISHER_REPRESENTATIVE = "PUBLISHER_REPRESENTATIVE";

    /** Display name for User role */
    String DISPLAY_NAME_USER = "User";
    /** Display Name for Admin role */
    String DiSPLAY_NAME_ADMIN = "Admin";
    /** Display Name for base Publisher Representative Role */
    String DISPLAY_NAME_BASE_ROLE_PUBLISHER_REPRESENTATIVE = "Publisher Representative For";

    /** Role separator */
    String SEPARATOR = "_";
    /** Role Prefix */
    String PREFIX = "ROLE_";

    /**
     * Creates the Representative Role for the publishing house
     * @param publishingHouse publishing house to create role for
     * @return Created role
     */
    RoleEntity createRepresentativeRoleForPublishingHouse(PublishingHouseEntity publishingHouse);

    /**
     * Gets the representative role name for the passed publishing house
     * @param publishingHouse publishing house to get representative role name for
     * @return representative role name for the publishing house
     */
    String getRepresentativeRoleNameForPublishingHouse(PublishingHouseEntity publishingHouse);

    /**
     * Gets the representative role display name for the publishing house
     * @param publishingHouseEntity publishing house to get the representative role display name for
     * @return representative role display name for publishing house
     */
    String getRepresentativeRoleDisplayNameForPublishingHouse(PublishingHouseEntity publishingHouseEntity);

    /**
     * Gets the Publishing House Representative Role for the publishing house
     * @param publishingHouseEntity publishing house to get the representative role for
     * @return Publishing House Representative role
     */
    RoleEntity getRepresentativeRoleForPublishingHouse(PublishingHouseEntity publishingHouseEntity);

    /**
     * Adds the user with the passed user id to the role for the passed role name
     * @param userId userId of user to which to add the role
     * @param roleName role name of role to add to the user
     * @return UserEntity
     */
    UserEntity addUserToRole(Long userId, String roleName);
}
