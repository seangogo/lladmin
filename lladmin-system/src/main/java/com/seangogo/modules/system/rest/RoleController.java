package com.seangogo.modules.system.rest;

import com.seangogo.modules.security.utils.JwtTokenUtil;
import com.seangogo.modules.system.service.RoleService;
import com.seangogo.modules.system.service.dto.RoleDTO;
import com.seangogo.modules.system.service.dto.RoleTreeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author seang
 * @date 2019/7/19 15:57
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 获取资源树
     *
     * @return ResourceInfo
     */
    @GetMapping
    @PreAuthorize("hasAuthority('role')")
    public RoleTreeDTO findTree() {
        return roleService.getTree();
    }

    /**
     * 根据查询所有角色
     *
     * @return List
     */
    @GetMapping(value = "select")
    @PreAuthorize("hasAuthority('user')")
    public List<Map<String, Object>> select() {
        String levelCode = jwtTokenUtil.getDeptLevelCode();
        return roleService.findLabel(levelCode);

    }
}
