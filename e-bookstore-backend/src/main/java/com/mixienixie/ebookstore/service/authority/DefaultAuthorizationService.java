package com.mixienixie.ebookstore.service.authority;

import com.mixienixie.ebookstore.repo.authority.entity.RoleEntity;
import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;
import com.mixienixie.ebookstore.service.AuthorizationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Default implementation for Authorization Service
 * @author ndjordjieski
 */
@Service
public class DefaultAuthorizationService implements AuthorizationService{

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            return null;
        }

        return (UserEntity) authentication.getPrincipal();
    }

    /**
     * {@inheritDoc}
     */
    @Override @SuppressWarnings("unchecked")
    public Set<RoleEntity> getAuthorizedUserRoles(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            return null;
        }

        return (Set<RoleEntity>) authentication.getAuthorities();
    }
}
