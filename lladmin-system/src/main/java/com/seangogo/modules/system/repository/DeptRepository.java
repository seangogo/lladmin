package com.seangogo.modules.system.repository;

import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.modules.system.domain.Dept;

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
    List<Dept> findByLevelCodeLike(String levelCode);
}
