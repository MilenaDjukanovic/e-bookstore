package com.mixienixie.ebookstore.repo.authority;

import com.mixienixie.ebookstore.repo.authority.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Role Repository
 * @author ndjordjieski
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

    /**
     * Find role by authority
     * @param authority authority
     * @return Role optional
     */
    Optional<RoleEntity> findByAuthority(String authority);

    /**
     * Finds role by role display name
     * @param displayName displayName of role
     * @return Role optional
     */
    Optional<RoleEntity> findByDisplayName(String displayName);
}
