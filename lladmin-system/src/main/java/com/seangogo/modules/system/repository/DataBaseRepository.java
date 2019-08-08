package com.seangogo.modules.system.repository;

import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.modules.system.domain.DataBase;
import com.seangogo.modules.system.domain.TableInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据库
 *
 * @author seang
 * @date 2019/8/7 10:47
 */
public interface DataBaseRepository extends BaseRepository<DataBase, Long> {

    /**
     * 查询树形数据
     *
     * @return tree
     */
    @EntityGraph(value = "database-table-field",
            type = EntityGraph.EntityGraphType.LOAD)
    @Query("select d FROM DataBase d ")
    List<DataBase> findTree();
}
