package com.mixienixie.ebookstore.repo.authority;

import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserEntity repository
 *
 * @author ndjordjieski
 */
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    /**
     * Finds the user by username
     *
     * @param username username of the user
     * @return User optional
     */
    Optional<UserEntity> findByUsername(String username);
}
