package com.custom.sean.common.service.impl;


import com.custom.sean.common.domain.Brand;
import com.custom.sean.common.domain.Project;
import com.custom.sean.common.repository.ProjectRepository;
import com.custom.sean.common.service.BrandService;
import com.custom.sean.common.service.ProjectService;
import com.custom.sean.common.utils.jpa.BaseRepository;
import com.custom.sean.common.utils.jpa.BaseServiceImpl;
import com.custom.sean.common.utils.vo.StateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sean
 * 2017/11/3.
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class ProjectServiceImpl extends BaseServiceImpl<Project, Long> implements ProjectService {

    private final ProjectRepository projectRepository;

    private final BrandService brandService;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, BrandServiceImpl brandServiceImpl) {
        this.projectRepository = projectRepository;
        this.brandService = brandServiceImpl;
    }

    @Override
    public BaseRepository<Project, Long> getBaseDao() {
        return this.projectRepository;
    }

    @Override
    public void updateOne(Long id, Project project){
        Project p = projectRepository.getOne(id);
        Brand brand=brandService.find(project.getBrand().getId());
        p.setBrand(brand);
        p.setRemark(project.getRemark());
        p.setName(project.getName());
        projectRepository.save(p);

    }
    @Override
    public boolean isUse(String code) {
        Project project = new Project();
        project.setCode(code);
        return !projectRepository.exists(Example.of(project));
    }

    /**
     * 新增项目
     *
     * @param project vo
     */
    @Override
    public StateResult saveModel(Project project) {
        Brand brand =brandService.find(project.getBrand().getId());
        project.setBrand(brand);
        save(project);
        return StateResult.success();
    }

    @Override
    public List<Project> list(String name, String brandId) {
//        Specification querySpecifi = (root, criteriaQuery, criteriaBuilder) -> {
//            root.fetch("brand", JoinType.LEFT);
//            List<Predicate> predicates = new ArrayList<>();
//            if (null != name) {
//                predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%"));
//            }
//            if (null != brandId) {
//                predicates.add(criteriaBuilder.equal(root.get("brand").get("id").as(String.class), brandId ));
//            }
//            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//        };
        return projectRepository.findAll();
    }

    @Override
    public Page page(Pageable pageable) {
        return projectRepository.page(pageable);
    }
}
