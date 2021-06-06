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
    String DISPLAY_NAME_ADMIN = "Admin";
    /** Display Name for Publisher Representative role */
    String DISPLAY_NAME_PUBLISHER_REPRESENTATIVE = "Publisher Representative";
    /** Display Name for base Publisher Representative Role */
    String DISPLAY_NAME_BASE_ROLE_PUBLISHER_REPRESENTATIVE = "Publisher Representative For";

    /** Role separator */
    String SEPARATOR = "_";
    /** Role Prefix */
    String PREFIX = "ROLE_";
    /** Prefix of the publishing hosue specific representative role */
    String PREFIX_PUBLISHING_HOUSE_REPRESENTATIVE = PREFIX + ROLE_PUBLISHER_REPRESENTATIVE + SEPARATOR;

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

    /**
     * Gets the Publishing House Representative role for the provided user for his publishing house
     * @param userId userId of user for which to get the role
     * @return Publishing House Representative role of user for his publishing house
     */
    RoleEntity getPublishingHouseRepresentativeRoleForUser(Long userId);

    /**
     * Returns the TIN of the Publishing House for which the user is a Publishing House Representative
     * @param userId id of user for which to return the TIN of publishing house
     * @return TIN of publishing house
     */
    String getPublishingHouseTinForPublishingHouseRepresentative(Long userId);
}
