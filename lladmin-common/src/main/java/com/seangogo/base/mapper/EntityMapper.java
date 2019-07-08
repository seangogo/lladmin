package com.seangogo.base.mapper;

import java.util.List;

/**
 * 部门数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
public interface EntityMapper<D, E> {

    /**
     * DTO转Entity
     *
     * @param dto
     * @return
     */
    E toEntity(D dto);

    /**
     * Entity转DTO
     *
     * @param entity
     * @return
     */
    D toDto(E entity);

    /**
     * DTO集合转Entity集合
     *
     * @param dtoList
     * @return
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Entity集合转DTO集合
     *
     * @param entityList
     * @return
     */
    List<D> toDto(List<E> entityList);
}
