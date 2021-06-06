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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.Objects;

/**
 * {@inheritDoc}
 *
 * Default implementation of the User Service
 * @author ndjordjieski
 */
@Service
@Transactional
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
    public UserEntity save(UserEntity userEntity){
        Objects.requireNonNull(userEntity);
        Objects.requireNonNull(userEntity.getId());

        this.userRepository.findById(userEntity.getId())
                .orElseThrow(() -> new EntityNotFoundException("Could not find user to update with id: " + userEntity.getId()));

        return this.userRepository.save(userEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDto createRepresentativeUser(CreateRepresentativeUserRequest createRepresentativeUserRequest){
        // Get the publishing house for the tin
        PublishingHouseEntity publishingHouseEntity = this.publishingHouseService.findByTin(createRepresentativeUserRequest.getTin());

        // Validate if user provided correct registration key
        if(!StringUtils.equals(publishingHouseEntity.getRepresentativeRegistrationKey(),
                createRepresentativeUserRequest.getRepresentativeRegistrationKey())){
            throw new ValidationException("Invalid registration key provided!");
        }

        // Create the base user
        UserEntity userEntity = this.createBaseUser(createRepresentativeUserRequest);

        // Add the user to the representative role for the house
        RoleEntity role = this.roleService.getRepresentativeRoleForPublishingHouse(publishingHouseEntity);
        this.roleService.addUserToRole(userEntity.getId(), role.getAuthority());
        // Also add the user to the base representative role
        this.roleService.addUserToRole(userEntity.getId(), RoleService.ROLE_PUBLISHER_REPRESENTATIVE);

        return this.userViewMapper.toDto(userEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity findById(Long id){
        Objects.requireNonNull(id);

        return this.userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find user with id: " + id));
    }

    /**
     * Creates a user with the ROLE_USER role
     * @param createUserRequest Request for user creation containing user details
     * @return UserEntity object for the created user
     */
    private UserEntity createBaseUser(CreateUserRequest createUserRequest){
        Objects.requireNonNull(createUserRequest);

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
