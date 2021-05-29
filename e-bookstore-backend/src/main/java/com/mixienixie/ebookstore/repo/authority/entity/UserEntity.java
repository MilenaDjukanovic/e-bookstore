package com.mixienixie.ebookstore.repo.authority.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * User Entity
 *
 * @author ndjordjieski
 */
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails{

    /** User ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    /** Username (email) of the user */
    @Column(nullable = false)
    @Setter
    private String username;

    /** User password */
    @Column(nullable = false)
    @Getter @Setter
    private String password;

    /** First Name */
    @Column(name = "first_name", nullable = false)
    @Getter @Setter
    private String firstName;

    /** Last Name */
    @Column(name = "last_name", nullable = false)
    @Getter @Setter
    private String lastName;

    /** User Authorities/Roles */
    @ManyToMany(fetch = FetchType.EAGER)
    @Setter
    private Set<RoleEntity> authorities = new HashSet<>();

    /** Flag whether user is enabled */
    @Setter
    private boolean enabled = true;

    /**
     * Getter for user authorities/groups
     * @return User authorities/groups
     */
    @Override
    public Collection<RoleEntity> getAuthorities(){
        return this.authorities;
    }

    /**
     * Getter for username
     * @return username
     */
    @Override
    public String getUsername(){
        return this.username;
    }

    /**
     * Checks if the user account is non expired
     * @return true if it's non expired, otherwise false
     */
    @Override
    public boolean isAccountNonExpired(){
        return this.enabled;
    }

    /**
     * Checks if the user account is non locked
     * @return true if it's non locked, otherwise false
     */
    @Override
    public boolean isAccountNonLocked(){
        return this.enabled;
    }

    /**
     * Checks if credentials are non expired
     * @return true if they are non expired, otherwise false
     */
    @Override
    public boolean isCredentialsNonExpired(){
        return this.enabled;
    }

    /**
     * Checks if the user account is enabled
     * @return true if it's enabled, otherwise false
     */
    @Override
    public boolean isEnabled(){
        return this.enabled;
    }
}
