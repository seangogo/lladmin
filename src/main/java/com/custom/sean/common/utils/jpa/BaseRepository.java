package com.custom.sean.common.utils.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Collection;

/**
 * JpaRepository接口提供crud和分页接口
 * JpaSpecificationExecutor提供Specification查询接口
 * @NoRepositoryBean让jpa:repositories扫描时忽略
 * @param <T>
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity,ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>, QuerydslPredicateExecutor<T> {

    T findByIdAndDelFlag(ID id, int i);

    Collection<T> findByIdIn(Iterable<ID> ids);
}
