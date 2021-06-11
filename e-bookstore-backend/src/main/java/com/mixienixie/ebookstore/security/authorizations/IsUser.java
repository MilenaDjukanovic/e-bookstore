package com.mixienixie.ebookstore.security.authorizations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Prevents access for non users
 * @author ndjordjieski
 */
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("(hasRole(T(com.mixienixie.ebookstore.service.RoleService).ROLE_USER))")
public @interface IsUser{
}
