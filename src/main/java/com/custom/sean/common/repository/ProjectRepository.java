package com.custom.sean.common.repository;


import com.custom.sean.common.domain.Project;
import com.custom.sean.common.utils.jpa.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author sean
 * 2017/11/3.
 */
public interface ProjectRepository extends BaseRepository<Project,String>{

    @Query("select new map(p.code as value,p.name as label,p.brand.id as bid, p.brand.name as bname) from Project p")
    List<Map<String,String>> findTree();

    @Override
    @EntityGraph(value = "ProjectInfo.detail",type = EntityGraph.EntityGraphType.LOAD)
    List<Project> findAll();

    @EntityGraph(value = "ProjectInfo.detail",type = EntityGraph.EntityGraphType.LOAD)
    @Query("select p from Project p")
    Page<Project> page(Pageable page);
}
