package com.seangogo.modules.system.rest;

import com.seangogo.common.utils.StateResult;
import com.seangogo.modules.security.utils.JwtTokenUtil;
import com.seangogo.modules.system.domain.Role;
import com.seangogo.modules.system.service.RoleService;
import com.seangogo.modules.system.service.dto.RoleTreeDTO;
import com.seangogo.modules.system.service.vo.DeptTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
     * 获取角色树
     *
     * @return ResourceInfo
     */
    @GetMapping
    @PreAuthorize("hasAuthority('role')")
    public RoleTreeDTO findTree() {
        return roleService.getTree();
    }

    /**
     * 创建角色
     *
     * @param role vo
     * @return success
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('role_opt')")
    public StateResult add(@RequestBody Role role) {
        roleService.create(role);
        return StateResult.success();
    }

    /**
     * 根据账户角色查询所有角色
     *
     * @return List
     */
    @GetMapping(value = "select")
    @PreAuthorize("hasAnyAuthority('user','role')")
    public DeptTree select() {
        String levelCode = jwtTokenUtil.getDeptLevelCode();
        return roleService.findLabel(levelCode);
    }

    /**
     * 回显授权过的全选和半选的资源
     *
     * @param id 角色Id
     * @return data
     */
    @GetMapping("/resourceIds/{id}")
    @PreAuthorize("hasAuthority('role_opt')")
    public Map<String, Object> findRoleIds(@PathVariable Long id) {
        return roleService.getRoleResources(id);
    }

    /**
     * 角色绑定资源
     *
     * @return success
     */
    @PostMapping(value = "/bindResource")
    @PreAuthorize("hasAuthority('role_opt')")
    public StateResult editBindResource(@RequestBody Map<String, String> map) {
        roleService.setRoleResources(Long.parseLong(map.get("roleId")), map.get("resourceIds"));
        return StateResult.success();
    }


}
