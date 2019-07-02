package com.custom.sean.common.service;


import com.custom.sean.common.domain.Project;
import com.custom.sean.common.utils.jpa.BaseService;
import com.custom.sean.common.utils.vo.StateResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author sean
 * 2017/11/3.
 */

public interface ProjectService extends BaseService<Project, Long> {

    void updateOne(Long id, Project project);

    boolean isUse(String code);

    /**
     * 新增项目
     *
     * @param project vo
     */
    StateResult saveModel(Project project);

    List<Project> list(String name, String brandId);

    Page page(Pageable pageable);

}
