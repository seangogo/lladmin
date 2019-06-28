package com.custom.sean.common.utils.jpa;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseService<T, ID extends Serializable> {
    /**
     * 根据主键查找
     * @param id 主键
     * @return 实体
     */
    T find(ID id);

    /**
     * 查询全部
     * @return 集合
     */
    List<T> findAll();

    /**
     * 根据主键集合查找
     * @param ids 主键集合
     * @return 集合
     */
    Collection<T> findList(Iterable<ID> ids);

    /**
     * 分页
     * @param pageable 分页对象
     * @return 数据和分页信息
     */
    Page<T> findAll(Pageable pageable);

    /**
     * 根据条件和分页对象查找
     * @param spec 动态条件
     * @param pageable 分页对象
     * @return 数据和分页信息
     */
    Page<T> findAll(Specification<T> spec, Pageable pageable);

    /**
     * 根据过滤对象和分页对象查询
     * @param example 过滤对象
     * @param pageable 分页对象
     * @return 数据和分页信息
     */
    Page<T> findAll(Example<T> example, Pageable pageable);

    /**
     * 根据过滤对象查询
     * @param example 过滤对象
     * @return 集合
     */
    List<T> findAll(Example<T> example);

    /**
     * 数据总条数
     * @return 条数
     */
    long count();

    /**
     * 根据条件查询总条数
     * @param spec 条件
     * @return 总条数
     */
    long count(Specification<T> spec);

    /**
     * 保存
     * @param entity 实体
     */
    void save(T entity);

    /**
     *  修改
     * @param entity
     * @return
     */
    T update(T entity);

    /**
     * 删除
     * @param id
     */
    void delete(ID id);

    /**
     * 删除集合
     * @param ids
     */
    void deleteByIds(ID... ids);

    /**
     * 删除集合
     * @param entitys
     */
    void delete(T[] entitys);

    /**
     * 删除集合
     * @param entitys
     */
    void delete(Iterable<T> entitys);

    /**
     * 删除
     * @param entity 实体
     */
    void delete(T entity);

    /**
     * 根据条件和排序查找
     * @param spec 条件
     * @param sort 排序
     * @return 集合
     */
    List<T> findList(Specification<T> spec, Sort sort);
}