package cn.seangogo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Collection;

/**
 * JpaRepository接口提供crud和分页接口
 * JpaSpecificationExecutor提供Specification查询接口
 * @author seang
 * @param <T>
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity,ID extends Serializable>
        extends JpaRepository<T, Serializable>, JpaSpecificationExecutor<T> {
    /**
     * 根據ids批量查找
     * @param ids 集合
     * @return entitys
     */
    Collection<T> findByIdIn(Iterable<ID> ids);
}
