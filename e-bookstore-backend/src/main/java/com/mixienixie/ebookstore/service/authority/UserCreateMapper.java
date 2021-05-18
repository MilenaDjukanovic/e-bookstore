package com.mixienixie.ebookstore.service.authority;

import com.mixienixie.ebookstore.authority.requests.CreateUserRequest;
import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;
import com.mixienixie.ebookstore.service.EntityMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * Mapper used for bi directional mapping between CreateUserRequest object and UserEntity object
 *
 * @author ndjordjieski
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserCreateMapper extends EntityMapper<CreateUserRequest, UserEntity>{

}
