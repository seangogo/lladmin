package com.custom.sean.common.repository.custom;

import com.custom.sean.common.domain.Project;
import com.custom.sean.common.domain.dto.ProjectBrandDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author seang
 */
@Repository
public class ProjectRepositoryCustomImpl implements ProjectRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ProjectBrandDTO> projectBrandName() {
        JPAQuery<Project> query = new JPAQuery<>(entityManager);
        List<ProjectBrandDTO> rpDTOList =
                query.select(Projections.constructor(ProjectBrandDTO.class,
                        QProject.project.name,
                        QProject.project.code,
                        QProject.project.brand.name,
                        QProject.project.brand.code))
                        .from(QProject.project)
                        .rightJoin(QProject.project.brand)
                        .orderBy(QProject.project.createdDate.desc(),QProject.project.updatedDate.asc())
                        .fetch();
        return rpDTOList;
    }
}
