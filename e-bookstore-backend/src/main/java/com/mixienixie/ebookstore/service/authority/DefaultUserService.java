package com.mixienixie.ebookstore.service.authority;

import com.mixienixie.ebookstore.authority.requests.CreateUserRequest;
import com.mixienixie.ebookstore.repo.authority.UserRepository;
import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;
import com.mixienixie.ebookstore.repo.authority.entity.UserView;
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
    public UserView create(CreateUserRequest createUserRequest){
        if(this.userRepository.findByUsername(createUserRequest.getUsername()).isPresent()){
            throw new ValidationException("Username exists");
        }
        if(!StringUtils.equals(createUserRequest.getPassword(), createUserRequest.getRePassword())){
            throw new ValidationException("Passwords don't match");
        }

        UserEntity user = this.userCreateMapper.toEntity(createUserRequest);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        user = this.userRepository.save(user);

        return this.userViewMapper.toDto(user);
    }
}
