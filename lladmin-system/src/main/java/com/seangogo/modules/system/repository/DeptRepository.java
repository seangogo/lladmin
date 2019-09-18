package com.seangogo.modules.system.repository;

import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.modules.system.domain.Dept;
import com.seangogo.modules.system.service.vo.DeptTreeInterface;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 部门数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
public interface DeptRepository extends BaseRepository<Dept, Long> {

    /**
     * 根据层级编码查找所有部门
     *
     * @param levelCode 层级编码
     * @return 部门集合
     */
    @Query("select d.id as id, d.name as name, d.parenId as parentId from Dept d where d.levelCode like ?1")
    List<DeptTreeInterface> findByLevelCodeLike(String levelCode);
}
