package com.mixienixie.ebookstore.repo.authority.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Role Entity
 *
 * @author nkurcubic
 */
@Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "roles")
public class RoleEntity implements GrantedAuthority{

    /** Role ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    /** Authority */
    @Column(nullable = false, unique = true)
    @Getter @Setter
    private String authority;

    /** Display Name of Role */
    @Column(nullable = false, unique = true)
    @Getter @Setter
    private String displayName;
}
