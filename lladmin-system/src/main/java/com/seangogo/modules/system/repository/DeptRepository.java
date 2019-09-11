package com.seangogo.modules.system.repository;

import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.modules.system.domain.Dept;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

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
    @Query("select new map(d.name as lable, d.id as value, d.pid as pid) from Dept d where d.levelCode like ?1")
    List<Map<String,Object>> findByLevelCodeLike(String levelCode);
}
