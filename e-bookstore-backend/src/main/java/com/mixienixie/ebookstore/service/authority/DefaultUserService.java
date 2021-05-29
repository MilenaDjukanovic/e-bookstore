package com.mixienixie.ebookstore.service.authority;

import com.mixienixie.ebookstore.authority.requests.CreateRepresentativeUserRequest;
import com.mixienixie.ebookstore.authority.requests.CreateUserRequest;
import com.mixienixie.ebookstore.repo.authority.UserRepository;
import com.mixienixie.ebookstore.repo.authority.entity.RoleEntity;
import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;
import com.mixienixie.ebookstore.repo.authority.entity.UserDto;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseEntity;
import com.mixienixie.ebookstore.service.PublishingHouseService;
import com.mixienixie.ebookstore.service.RoleService;
import com.mixienixie.ebookstore.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

/**
 * {@inheritDoc}
 *
 * Default implementation of the User Service
 * @author ndjordjieski
 */
@Service
@AllArgsConstructor
public class DefaultUserService implements UserService{

    /** User Repository */
    private final UserRepository userRepository;

    /** Role Repository */
    private final RoleService roleService;

    /** Publishing House Service */
    private final PublishingHouseService publishingHouseService;

    /** User View Mapper */
    private final UserViewMapper userViewMapper;
    /** User Create Mapper */
    private final UserCreateMapper userCreateMapper;

    /** Password Encoder */
    private final PasswordEncoder passwordEncoder;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDto createUser(CreateUserRequest createUserRequest){
        UserEntity userEntity = this.createBaseUser(createUserRequest);

        return this.userViewMapper.toDto(userEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDto createRepresentativeUser(CreateRepresentativeUserRequest createRepresentativeUserRequest){
        // Create the base user
        UserEntity userEntity = this.createBaseUser(createRepresentativeUserRequest);

        PublishingHouseEntity publishingHouseEntity =
                this.publishingHouseService.findPublishingHouseEntityByRepresentativeRegistrationKey(
                        createRepresentativeUserRequest.getRepresentativeRegistrationKey());

        // Add the user to the representative role for the house
        RoleEntity role = this.roleService.getRepresentativeRoleForPublishingHouse(publishingHouseEntity);
        this.roleService.addUserToRole(userEntity.getId(), role.getAuthority());
        // Also add the user to the base representative role
        this.roleService.addUserToRole(userEntity.getId(), RoleService.ROLE_PUBLISHER_REPRESENTATIVE);

        return this.userViewMapper.toDto(userEntity);
    }

    /**
     * Creates a user with the ROLE_USER role
     * @param createUserRequest Request for user creation containing user details
     * @return UserEntity object for the created user
     */
    private UserEntity createBaseUser(CreateUserRequest createUserRequest){
        if(this.userRepository.findByUsername(createUserRequest.getUsername()).isPresent()){
            throw new ValidationException("Username exists");
        }
        if(!StringUtils.equals(createUserRequest.getPassword(), createUserRequest.getRePassword())){
            throw new ValidationException("Passwords don't match");
        }

        UserEntity userEntity = this.userCreateMapper.toEntity(createUserRequest);
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));

        userEntity = this.userRepository.save(userEntity);

        userEntity = this.roleService.addUserToRole(userEntity.getId(), RoleService.ROLE_USER);

        return userEntity;
    }
}
