package com.mixienixie.ebookstore.rest.authority.controller;

import com.mixienixie.ebookstore.authority.requests.AuthUserRequest;
import com.mixienixie.ebookstore.authority.requests.CreateRepresentativeUserRequest;
import com.mixienixie.ebookstore.authority.requests.CreateUserRequest;
import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;
import com.mixienixie.ebookstore.repo.authority.entity.UserDto;
import com.mixienixie.ebookstore.service.UserService;
import com.mixienixie.ebookstore.service.authority.UserViewMapper;
import com.mixienixie.ebookstore.service.security.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller for handling authentication/authorization related requests
 *
 * @author ndjordjieski
 */
@RestController @RequestMapping("/api/public/auth")
@AllArgsConstructor
public class AuthController{

    /** Authentication manager */
    private final AuthenticationManager authenticationManager;
    /** JWT Token Util */
    private final JwtTokenUtil jwtTokenUtil;
    /** User View Mapper */
    private final UserViewMapper userViewMapper;
    /** User Service */
    private final UserService userService;

    /**
     * Handles the user login request
     * @param authUserRequest user login details
     *
     * @return User if login is successful, otherwise returns Unauthorized status
     */
    @PostMapping("login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid AuthUserRequest authUserRequest){
        try{
            Authentication authentication = this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authUserRequest.getUsername(), authUserRequest.getPassword()));

            UserEntity user = (UserEntity) authentication.getPrincipal();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, this.jwtTokenUtil.generateAccessToken(user))
                    .body(this.userViewMapper.toDto(user));
        }catch(BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * Handles the register user request
     * @param createUserRequest User registration details
     *
     * @return User if registration is successful, otherwise error is thrown
     */
    @PostMapping("register/user")
    public UserDto register(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return this.userService.createUser(createUserRequest);
    }

    /**
     * Handles the register representative request
     * @param createRepresentativeUserRequest Representative User registration details
     * @return User if registration is successful, otherwise error is thrown
     */
    @PostMapping("register/representative")
    public UserDto register(@RequestBody @Valid CreateRepresentativeUserRequest createRepresentativeUserRequest){
        return this.userService.createRepresentativeUser(createRepresentativeUserRequest);
    }

}
