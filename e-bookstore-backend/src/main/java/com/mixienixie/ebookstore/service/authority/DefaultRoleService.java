package com.mixienixie.ebookstore.service.authority;

import com.mixienixie.ebookstore.repo.authority.RoleRepository;
import com.mixienixie.ebookstore.repo.authority.UserRepository;
import com.mixienixie.ebookstore.repo.authority.entity.RoleEntity;
import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;
import com.mixienixie.ebookstore.repo.core.PublishingHouseRepository;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseEntity;
import com.mixienixie.ebookstore.service.PublishingHouseService;
import com.mixienixie.ebookstore.service.RoleService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.Objects;

/**
 * Default implementation of the Role Service
 * @author ndjordjieski
 */
@Service
@AllArgsConstructor
public class DefaultRoleService implements RoleService{

    /** Role Service */
    private final RoleRepository roleRepository;

    /** User Repository */
    private final UserRepository userRepository; // #TODO this might need to be refactored

    /** Publishing House Service */
    private final PublishingHouseRepository publishingHouseRepository; // #TODO this might need refactoring

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleEntity createRepresentativeRoleForPublishingHouse(PublishingHouseEntity publishingHouse){
        Objects.requireNonNull(publishingHouse);
        Objects.requireNonNull(publishingHouse.getId());

        // Handle case where we just
        if(StringUtils.isEmpty(publishingHouse.getTin())){
            String tin = publishingHouse.getTin();
            publishingHouse = this.publishingHouseRepository.findByTin(tin)
                    .orElseThrow(() -> new EntityNotFoundException("Could not find publishing house for id: " + tin));
        }

        String roleName = this.getRepresentativeRoleNameForPublishingHouse(publishingHouse);
        RoleEntity role = new RoleEntity();
        role.setAuthority(roleName);

        String roleDisplayName = this.getRepresentativeRoleDisplayNameForPublishingHouse(publishingHouse);
        role.setDisplayName(roleDisplayName);

        return this.roleRepository.save(role);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRepresentativeRoleNameForPublishingHouse(PublishingHouseEntity publishingHouse){
        return PREFIX + ROLE_PUBLISHER_REPRESENTATIVE + SEPARATOR + publishingHouse.getTin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRepresentativeRoleDisplayNameForPublishingHouse(PublishingHouseEntity publishingHouse){
        return DISPLAY_NAME_BASE_ROLE_PUBLISHER_REPRESENTATIVE + " " + publishingHouse.getCompanyName()
                + " (" + publishingHouse.getTin() + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleEntity getRepresentativeRoleForPublishingHouse(PublishingHouseEntity publishingHouse){
        String roleDisplayName = this.getRepresentativeRoleNameForPublishingHouse(publishingHouse);
        return this.roleRepository.findByAuthority(roleDisplayName)
                .orElseThrow(() -> new EntityNotFoundException("Publishing house with id: " + publishingHouse.getId() + " not found!"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity addUserToRole(Long userId, String roleName){
        Objects.requireNonNull(userId);
        Objects.requireNonNull(roleName);

        if(!StringUtils.startsWith(roleName, PREFIX)){
            roleName = PREFIX + roleName;
        }

        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find user for id: " + userId));

        String finalRoleName = roleName;
        RoleEntity roleEntity = this.roleRepository.findByAuthority(roleName)
                .orElseThrow(() -> new EntityNotFoundException("Role with roleName: " + finalRoleName + " not found!"));

        userEntity.getAuthorities().add(roleEntity);

        return this.userRepository.save(userEntity);
    }

    @Override
    public RoleEntity getPublishingHouseRepresentativeRoleForUser(Long userId){
        Objects.requireNonNull(userId);

        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find user with id: " + userId));

        return this.getPublishingHouseRepresentativeRoleForUser(userEntity);
    }

    @Override
    public String getPublishingHouseTinForPublishingHouseRepresentative(Long userId){
        RoleEntity publishingHouseRepresentativeRole = this.getPublishingHouseRepresentativeRoleForUser(userId);
        return StringUtils.substringAfter(publishingHouseRepresentativeRole.getAuthority(), PREFIX_PUBLISHING_HOUSE_REPRESENTATIVE);
    }

    private RoleEntity getPublishingHouseRepresentativeRoleForUser(UserEntity userEntity){
        return userEntity.getAuthorities().stream()
                .filter(roleEntity -> StringUtils.startsWith(roleEntity.getAuthority(), PREFIX_PUBLISHING_HOUSE_REPRESENTATIVE))
                .findFirst()
                .orElseThrow(() -> new ValidationException("User with id: " + userEntity.getId() + " is not a Publishing House Representative"));
    }

}
