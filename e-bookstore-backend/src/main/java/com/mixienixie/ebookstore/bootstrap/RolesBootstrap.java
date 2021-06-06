package com.mixienixie.ebookstore.bootstrap;

import com.mixienixie.ebookstore.repo.authority.RoleRepository;
import com.mixienixie.ebookstore.repo.authority.entity.RoleEntity;
import com.mixienixie.ebookstore.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Creates the base roles, that are needed for the application to work, if they do not exist
 * @author ndjordjieski
 */
@Component
public class RolesBootstrap implements CommandLineRunner{

    /** Role Repository */
    @Autowired
    private RoleRepository roleRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(String... args) throws Exception{
        this.createIfNotExistRole(RoleService.ROLE_ADMIN, RoleService.DISPLAY_NAME_ADMIN);
        this.createIfNotExistRole(RoleService.ROLE_USER, RoleService.DISPLAY_NAME_USER);
        this.createIfNotExistRole(RoleService.ROLE_PUBLISHER_REPRESENTATIVE, RoleService.DISPLAY_NAME_PUBLISHER_REPRESENTATIVE);
    }

    /**
     * Creates the role with the passed authority and display name, if it does not exist
     * @param authority authority
     * @param displayName displayName
     */
    private void createIfNotExistRole(String authority, String displayName){
        if(!StringUtils.startsWith(authority, RoleService.PREFIX)){
            authority = RoleService.PREFIX + authority;
        }

        RoleEntity roleEntity = this.roleRepository.findByAuthority(authority).orElse(null);
        if(roleEntity == null){
            roleEntity = new RoleEntity();
            roleEntity.setAuthority(authority);
            roleEntity.setDisplayName(displayName);

            this.roleRepository.save(roleEntity);
        }
    }
}
