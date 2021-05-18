package com.mixienixie.ebookstore.service;

import java.util.List;

/**
 * Interface that allows mapping between entity and DTO objects.
 *
 * @author ndjordjieski
 */
public interface EntityMapper <D, E> {

    /**
     * Maps DTO to Entity
     * @param dto DTO
     * @return Entity
     */
    E toEntity(D dto);

    /**
     * Maps Entity  to DTO
     * @param entity Entity
     * @return DTO
     */
    D toDto(E entity);

    /**
     * Maps list of DTOs to list of Entities
     * @param dtoList DTO list
     * @return List of Entities
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Maps list of Entities to list of DTOs
     * @param entityList Entity list
     * @return List of DTOs
     */
    List <D> toDto(List<E> entityList);
}
