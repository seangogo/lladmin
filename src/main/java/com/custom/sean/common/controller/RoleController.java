package com.custom.sean.common.controller;

import com.custom.sean.common.domain.Role;
import com.custom.sean.common.service.RoleService;
import com.custom.sean.common.utils.basics.JwtTokenUtil;
import com.custom.sean.common.utils.vo.PageResult;
import com.custom.sean.common.utils.vo.StateResult;
import com.custom.sean.common.utils.vo.rbac.RoleTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sean
 * @date 2017/11/8
 */
@RestController
@RequestMapping("auth/role")
public class RoleController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RoleService roleService;

    /**
     * 获取资源树
     *
     * @return ResourceInfo
     */
    @GetMapping
    @PreAuthorize("hasAuthority('role')")
    public RoleTree findTree() {
        return roleService.getTree();
    }

    /**
     * 角色动态动态列表查询
     *
     * @param pageable 分页对象
     * @param name 条件
     * @return page
     */
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('role')")
    public PageResult searchList(@PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable,
                                 @RequestParam(required = false) String name){
        String orgCode = jwtTokenUtil.getOrgCode();
        String username = jwtTokenUtil.getUserName();
        Specification querySpecifi = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (null != name) {
                predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%"));
            }
            if (!"pateo".equals(orgCode)) {
                predicates.add(criteriaBuilder.equal(root.get("createdBy").as(String.class), username));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        Page<Role> page = roleService.findAll(querySpecifi, pageable);
        return PageResult.getPageVo(page);
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
     * 修改角色
     *
     * @param id 角色id
     * @param roleVo vo
     * @return success
     */
    @PutMapping(value = "/edit/{id}")
    @PreAuthorize("hasAuthority('role_opt')")
    public StateResult edit(@PathVariable Long id,
                            @RequestBody Role roleVo) {
        roleService.update(id,roleVo);
        return StateResult.success();
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return success
     */
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('role_opt')")
    public StateResult delete(@PathVariable Long id){
        roleService.deleteRole(id);
        return StateResult.success();
    }

    /**
     * 角色批量删除
     *
     * @param id      角色id
     * @return success
     */
    @DeleteMapping(value = "/batchesRemove/{id}")
    @PreAuthorize("hasAuthority('role_opt')")
    public StateResult batchesRemove(@PathVariable String id){
        roleService.batchesDelete(id);
        return StateResult.success();
    }

    /**
     * 回显授权过的全选和半选的资源
     *
     * @param id      角色Id
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
    public StateResult editBindResource(@RequestBody Map<String, String> map){
        roleService.setRoleResources(Long.valueOf(map.get("roleId")), map.get("resourceIds"));
        return StateResult.success();
    }

//    /**
//     * 获取根据角色id查看关联的资源
//     *
//     * @param id      角色Id
//     * @return ResourceInfos
//     */
//    @GetMapping("/resourceTree/{id}")
//    @PreAuthorize("hasAuthority('role_opt')")
//    public List<ResourceInfo> findRoleTree(@PathVariable String id) {
//        List<Resource> resources = roleResourceRepository.findResourceByRole_Id(id);
//        return resourceService.toTree(resources, resourceService.getRootResource()).getChildren();
//    }

    /**
     * 根据查询所有角色
     *
     * @return List
     */
    @GetMapping(value = "select")
    @PreAuthorize("hasAuthority('user')")
    public List<Map<String, String>> select() {
        String username = jwtTokenUtil.getUserName();
        return roleService.findLabel(username);
    }

}
