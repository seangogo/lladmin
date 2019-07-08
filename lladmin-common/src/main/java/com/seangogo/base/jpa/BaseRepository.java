package com.seangogo.base.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Collection;

/**
 * 基础仓库类
 *
 * @param <T>  实体类型
 * @param <ID> 主键
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    /**
     * 通过主键逻辑删除
     *
     * @param id 主键
     */
    @Override
    @Modifying
    @Query("update #{#entityName} e set e.delFlag=true where e.id=?1")
    void deleteById(ID id);

    /**
     * 通过对象逻辑删除
     *
     * @param t
     */
    @Override
    @Modifying
    @Query("update #{#entityName} e set e.delFlag=true where e=?1")
    void delete(T t);

    /**
     * 通过对象批量删除
     *
     * @param entitys 对象集合
     */
    @Override
    @Modifying
    @Query("update #{#entityName} e set e.delFlag=true where e in ?1")
    void deleteAll(Iterable<? extends T> entitys);
}
