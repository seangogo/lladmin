package com.custom.sean.common.controller;

import com.custom.sean.common.domain.Project;
import com.custom.sean.common.domain.dto.ProjectBrandDTO;
import com.custom.sean.common.service.ProjectService;
import com.custom.sean.common.utils.vo.DataResult;
import com.custom.sean.common.utils.vo.StateResult;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sean
 * 2017/11/3.
 */
@RestController
@RequestMapping("auth/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    /**
     * 创建项目
     *
     * @param project vo
     * @return 成功结果
     */
    @PostMapping("/add")
    public StateResult add(@RequestBody Project project){
        return projectService.saveModel(project);
    }

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    @GetMapping("/page")
    public DataResult page(Pageable pageable) {
        return DataResult.success(projectService.page(pageable));
    }

    /**
     * 单个查询
     * @param project
     * @return
     */
    @GetMapping("/{id}")
    public DataResult showUserForm(@PathVariable("id") Project project) {
        return DataResult.success(project);
    }

    /**
     * 修改项目
     *
     * @param id 项目id
     * @param project vo
     * @return StateResult
     * @throws Exception Exception
     */
    @PutMapping("/edit/{id}")
    public StateResult edit(@PathVariable String id,
                            @RequestBody Project project){
        projectService.updateOne(id, project);
        return StateResult.success();
    }

    /**
     * 删除项目
     *
     * @param id 项目主键
     * @return 成功结果
     */
    @DeleteMapping(value = "/delete/{id}")
    public StateResult deleteProject(@PathVariable String id) {
        projectService.delete(id);
        return StateResult.success();
    }

    /**
     * 列表查询
     *
     * @param name 查询条件项目名称
     * @param brandId 查询条件品牌主键
     * @return 项目列表
     */
    @GetMapping(value = "/list")
    public List<Project> findAll(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String brandId) {
        return  projectService.list(name,brandId);
    }

    /**
     * code是否存在
     *
     * @param code 项目编码
     * @return true 可使用
     */
    @GetMapping("/isUse/{code}")
    public boolean findIsUse(@PathVariable String code) {
        return projectService.isUse(code);
    }

}
