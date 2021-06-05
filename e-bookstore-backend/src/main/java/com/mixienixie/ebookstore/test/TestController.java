package com.mixienixie.ebookstore.test;

import com.mixienixie.ebookstore.core.requests.CreatePublishingHouseRequest;
import com.mixienixie.ebookstore.repo.authority.RoleRepository;
import com.mixienixie.ebookstore.repo.authority.UserRepository;
import com.mixienixie.ebookstore.repo.authority.entity.RoleEntity;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseDto;
import com.mixienixie.ebookstore.service.PublishingHouseService;
import com.mixienixie.ebookstore.service.RoleService;
import com.mixienixie.ebookstore.service.UserService;
import com.mixienixie.ebookstore.service.core.PublishingHouseViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ndjordjieski
 */
@RestController @RequestMapping("/test")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class TestController{

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublishingHouseService publishingHouseService;

    @Autowired
    private PublishingHouseViewMapper publishingHouseViewMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("user")
    public PublishingHouseDto user(@RequestBody @Valid CreatePublishingHouseRequest createPublishingHouseRequest){
        PublishingHouseDto x = this.publishingHouseService.create(createPublishingHouseRequest);

        System.out.println(this.roleService.getRepresentativeRoleForPublishingHouse(this.publishingHouseViewMapper.toEntity(x)));

        return x;
    }

    @GetMapping("admin")
    public String admin(){
//        CreateUserRequest createUserRequest = new CreateUserRequest();
//        createUserRequest.setFirstName("Pera");
//        createUserRequest.setLastName("Zdera");
//        createUserRequest.setUsername("pera@zdera.com");
//        createUserRequest.setPassword("123");
//        createUserRequest.setRePassword("123");

        this.roleService.getPublishingHouseTinForPublishingHouseRepresentative(13L);
        return "ADMIN";
    }

    @GetMapping("init")
    public RoleEntity init() throws JSONException{
        
        return null;
    }
}
