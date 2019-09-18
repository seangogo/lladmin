package com.seangogo.modules.system.service;

import com.seangogo.base.jpa.BaseService;
import com.seangogo.modules.system.domain.Role;
import com.seangogo.modules.system.domain.User;
import com.seangogo.modules.system.service.dto.RoleTreeDTO;
import com.seangogo.modules.system.service.vo.DeptTree;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;
import java.util.Map;

/**
 * 角色业务层
 *
 * @author seang
 * @date 2019/7/19 15:59
 */
@CacheConfig(cacheNames = "role")
public interface RoleService extends BaseService<Role, Long> {

    RoleTreeDTO getTree();

    /**
     * 根据项目code查询所有角色
     * @param levelCode 管理员所在部门层级编码
     * @return 所在部门的默认角色
     */
    DeptTree findLabel(String levelCode);

    void create(Role role);

    /**
     * 回显授权过的全选和半选的资源
     *
     * @param id      角色Id
     * @return data
     */
    Map<String,Object> getRoleResources(Long id);

    /**
     * 角色绑定资源
     * @param roleId 角色id
     * @param resourceIds 绑定的资源ids
     */
    void setRoleResources(Long roleId, String resourceIds);
}
