package com.seangogo.modules.system.rest;

import com.seangogo.common.utils.DataResult;
import com.seangogo.modules.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门
 *
 * @author seang
 * @date 2019/8/6 15:10
 */
@RestController
@RequestMapping("dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 获取资源树
     *
     * @return ResourceInfo
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('dept')")
    public DataResult findTree(@RequestParam(required = false) String name) {
        return DataResult.success(deptService.getTree(name));
    }
}
