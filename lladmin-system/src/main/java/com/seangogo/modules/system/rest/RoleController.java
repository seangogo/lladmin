package com.seangogo.modules.system.rest;

import com.seangogo.modules.security.utils.JwtTokenUtil;
import com.seangogo.modules.system.domain.Role;
import com.seangogo.modules.system.service.RoleService;
import com.seangogo.modules.system.service.dto.RoleDTO;
import com.seangogo.modules.system.service.dto.RoleTreeDTO;
import com.seangogo.modules.system.service.vo.DeptTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
     * 创建角色
     *
     * @param role vo
     * @return success
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('role_opt')")
    public ResponseEntity add(@RequestBody Role role) {
        roleService.create(role);
        return new ResponseEntity(HttpStatus.OK);
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


}
