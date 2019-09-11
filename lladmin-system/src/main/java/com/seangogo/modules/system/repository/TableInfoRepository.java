package com.seangogo.modules.system.repository;

import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.modules.system.domain.TableInfo;
import com.seangogo.modules.system.service.dto.TableInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

/**
 * 表信息
 *
 * @author seang
 * @date 2019/8/7 10:57
 */

public interface TableInfoRepository extends BaseRepository<TableInfo, Long> {
    /**
     * 分页
     *
     * @param specification 查询条件对象
     * @param pageable 分页对象
     * @return 分页
     */
    @Override
    @EntityGraph(value = "table-field-graph",
            type = EntityGraph.EntityGraphType.FETCH)
    Page<TableInfoDto> findAll(Specification specification, Pageable pageable);
}
