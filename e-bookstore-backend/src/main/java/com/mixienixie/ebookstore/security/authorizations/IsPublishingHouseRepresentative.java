package com.mixienixie.ebookstore.security.authorizations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Prevents access for users that are not publishing house representatives
 * @author ndjordjieski
 */
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("(hasRole(T(com.mixienixie.ebookstore.service.RoleService).ROLE_USER) " +
        "&& hasRole(T(com.mixienixie.ebookstore.service.RoleService).ROLE_PUBLISHER_REPRESENTATIVE)) " +
        "|| hasRole(T(com.mixienixie.ebookstore.service.RoleService).ROLE_ADMIN)")
public @interface IsPublishingHouseRepresentative{
}
