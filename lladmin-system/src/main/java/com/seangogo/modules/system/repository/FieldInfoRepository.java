package com.seangogo.modules.system.repository;

import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.modules.system.domain.DataBase;
import com.seangogo.modules.system.domain.FieldInfo;
import com.seangogo.modules.system.domain.TableInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author seang
 * @date 2019/8/7 14:38
 */
public interface FieldInfoRepository extends BaseRepository<FieldInfo, Long> {

    /**
     * 查询树形数据
     *
     * @return tree
     */
    @EntityGraph(value = "database-table-field-graph",
            type = EntityGraph.EntityGraphType.LOAD)
    @Query("select f.tableInfo FROM FieldInfo f ")
    List<TableInfo> findTree();
}
