package com.mixienixie.ebookstore.service.core;

import com.mixienixie.ebookstore.repo.core.entity.AuthorDto;
import com.mixienixie.ebookstore.repo.core.entity.AuthorEntity;
import com.mixienixie.ebookstore.service.EntityMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * Mapper used for bi directional mapping between AuthorDto object and AuthorEntity object
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AuthorViewMapper extends EntityMapper<AuthorDto, AuthorEntity> {
}
