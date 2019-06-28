package com.custom.sean.common.repository;

import com.custom.sean.common.domain.Resource;
import com.custom.sean.common.utils.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * 资源相关数据操作
 * @author sean
 * @date 2017/11/6
 */
public interface ResourceRepository extends BaseRepository<Resource,String> {
    /**
     * 通过资源名称查找资源
     * @param name 资源名称
     * @return
     */
    Resource findByName(String name);

    /**
     * 根据一级菜单的name条件查询所有资源
     * @param name 一级菜单名称
     * @param root 根节点
     * @return 所有资源
     */
    List<Resource> findByNameLikeAndParentOrderBySort(String name, Resource root);

    /**
     * 通过名称code查找资源
     * @param code
     * @return
     */
    Resource findByCode(String code);

    /**
     * 根据ids 查询 resource
     * @param ids 主键集合
     * @return resources
     */
    List<Resource> findByIdIn(Set<String> ids);

    /**
     * 查询所有资源的父资源
     * @param allResourceIds
     * @return ids
     */
    @Query(value = "SELECT r.parent.id FROM Resource r WHERE r.id IN ?1 ")
    Set<String> findPidByIdIn(Object[] allResourceIds);

    /**
     * 根据父id查询所有子资源
     * @param id 父id
     * @return 子资源集合
     */
    List<Resource> findByParent_Id(String id);

    /** 查找排序
     * @return 最大排序数
     */
    @Query("select max (r.sort) from Resource r ")
    int findMaxSort();

}
