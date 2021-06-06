package com.mixienixie.ebookstore.bootstrap;

import com.mixienixie.ebookstore.repo.authority.RoleRepository;
import com.mixienixie.ebookstore.repo.authority.UserRepository;
import com.mixienixie.ebookstore.repo.authority.entity.RoleEntity;
import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;
import com.mixienixie.ebookstore.service.RoleService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Creates the admin user and the admin role if the admin user dose not exist
 * @author ndjorjieski
 */
@Component
public class DefaultAdminUserBootstrap implements CommandLineRunner{

    /** User Repository */
    @Autowired @Setter
    private UserRepository userRepository;

    /** Role Repository */
    @Autowired @Setter
    private RoleRepository roleRepository;

    /** Password Encoder */
    @Autowired @Setter
    private PasswordEncoder passwordEncoder;

    /** Default Admin Username */
    @Value("${ebookstore.bootstrap.default-admin-user.username}") @Setter
    private String defaultUsername;

    /** Default Admin Password */
    @Value("${ebookstore.bootstrap.default-admin-user.password}") @Setter
    private String defaultPassword;

    /** Default Admin First Name */
    @Value("${ebookstore.bootstrap.default-admin-user.first-name}") @Setter
    private String defaultFirstName;

    /** Default Admin Last Name */
    @Value("${ebookstore.bootstrap.default-admin-user.last-name}") @Setter
    private String defaultLastName;

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(String... args) throws Exception{
        UserEntity adminUser = this.userRepository.findByUsername(this.defaultUsername)
                .orElse(null);
        if(adminUser == null){
            RoleEntity adminRole = this.getOrCreateAdminRole();

            adminUser = this.createDefaultAdminUserEntityObject(adminRole);

            this.userRepository.save(adminUser);
        }
    }

    /**
     * Creates the default admin entity object with the provided default values. Does not save it in the database
     * @param adminRole - Admin RoleEntity
     * @return UserEntity for admin
     */
    private UserEntity createDefaultAdminUserEntityObject(RoleEntity adminRole){
        UserEntity adminUser = new UserEntity();
        adminUser.setUsername(this.defaultUsername);
        adminUser.setPassword(this.defaultUsername);
        adminUser.getAuthorities().add(adminRole);
        adminUser.setFirstName(this.defaultFirstName);
        adminUser.setLastName(this.defaultLastName);

        adminUser.setPassword(this.passwordEncoder.encode(this.defaultPassword));

        return adminUser;
    }

    /**
     * Creates the admin role in the database if it does not exist, then returns the admin role from the database
     * @return RoleEntity for the admin role
     */
    private RoleEntity getOrCreateAdminRole(){
        String adminAuthority = RoleService.PREFIX + RoleService.ROLE_ADMIN;
        RoleEntity adminRole = this.roleRepository.findByAuthority(adminAuthority).orElse(null);
        if(adminRole == null){
            adminRole = new RoleEntity();
            adminRole.setAuthority(adminAuthority);
            adminRole.setDisplayName(RoleService.DISPLAY_NAME_ADMIN);

            adminRole = this.roleRepository.save(adminRole);
        }

        return adminRole;
    }
}
