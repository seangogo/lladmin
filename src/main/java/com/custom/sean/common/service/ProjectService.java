package com.custom.sean.common.service;


import com.custom.sean.common.domain.Brand;
import com.custom.sean.common.domain.Project;
import com.custom.sean.common.domain.dto.ProjectBrandDTO;
import com.custom.sean.common.repository.ProjectRepository;
import com.custom.sean.common.utils.jpa.BaseRepository;
import com.custom.sean.common.utils.jpa.BaseService;
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

public interface ProjectService extends BaseService<Project,String> {

    void updateOne(String id, Project project);

    boolean isUse(String code) ;
    /**
     * 新增项目
     *
     * @param project vo
     */
    StateResult saveModel(Project project) ;

    List<Project> list(String name, String brandId) ;

    Page page(Pageable pageable) ;

    List<ProjectBrandDTO> queryDslSelect() ;
}
