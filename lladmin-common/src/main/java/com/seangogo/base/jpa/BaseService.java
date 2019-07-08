package com.seangogo.base.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, ID extends Serializable> {
    /**
     * 根据主键查找
     *
     * @param id 主键
     * @return 实体
     */
    T find(ID id);

    /**
     * 查询全部
     *
     * @return 集合
     */
    List<T> findAll();


    /**
     * 分页
     *
     * @param pageable 分页对象
     * @return 数据和分页信息
     */
    Page<T> findAll(Pageable pageable);

}