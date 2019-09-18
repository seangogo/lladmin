package com.seangogo.modules.system.repository;

import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.modules.system.domain.Resource;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * 资源数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:33
 */
public interface ResourceRepository extends BaseRepository<Resource, Long> {
    /**
     * 根据一级菜单的name条件查询所有资源
     *
     * @param name 一级菜单名称
     * @param root 根节点
     * @return 所有资源
     */
    List<Resource> findByNameLikeAndParentOrderBySort(String name, Resource root);

    /**
     * 查找排序
     *
     * @return 最大排序数
     */
    @Query("select max (r.sort) from Resource r ")
    int findMaxSort();

    /**
     * 查询所有资源的父资源
     * @param  ids resource
     * @return ids parentIds
     */
    @Query(value = "SELECT r.parent.id FROM Resource r WHERE r.id IN ?1 ")
    Set<Long> findPidByIdIn(List<Long> ids);

    List<Resource> findByIdIn(Set<Long> longs);
}
