package com.custom.sean.common.service;

import com.custom.sean.common.domain.Role;
import com.custom.sean.common.utils.jpa.BaseService;
import com.custom.sean.common.utils.vo.rbac.RoleTree;

import java.util.List;
import java.util.Map;

/**
 *
 * @author sean
 * @date 2017/11/6
 */
public interface RoleService extends BaseService<Role,Long> {

    /**
     * 获取当前账户角色tree
     *
     * @return
     */
    RoleTree getTree();

    /**
     * 树形资源递归
     *
     * @param roles
     * @param parent
     */
    RoleTree toTree(List<Role> roles, Role parent);

    /**
     * 新增角色
     * @param role
     */

    void create(Role role);
    
    /**
     * 通过roleId 查询资源id
     * @param roleId
     * @return
     */
    Map<String,Object> getRoleResources(Long roleId);

    /**
     * 角色绑定资源
     * @param roleId 角色id
     * @param resourceIds 绑定的资源ids
     */
    void setRoleResources(Long roleId, String resourceIds);

    /**
     * 删除角色
     * @param id
     */
    void deleteRole(Long id);

    /**
     * 根据项目code查询所有角色
     *
     * @return
     * @throws Exception
     */
    List<Map<String,String>> findLabel(String username);

    /**
     * 修改角色
     * @param id 角色id
     * @param roleVo vo
     */
    void update(Long id, Role roleVo);


    /**
     * 批量删除
     * @param id 角色id
     */
    void batchesDelete(String id);
}
