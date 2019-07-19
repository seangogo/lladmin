package com.seangogo.modules.system.service;

import com.seangogo.base.jpa.BaseService;
import com.seangogo.modules.system.domain.Role;
import com.seangogo.modules.system.domain.User;
import com.seangogo.modules.system.service.dto.RoleTreeDTO;
import org.springframework.cache.annotation.CacheConfig;

/**
 * 角色业务层
 *
 * @author seang
 * @date 2019/7/19 15:59
 */
@CacheConfig(cacheNames = "role")
public interface RoleService extends BaseService<Role, Long> {

    RoleTreeDTO getTree();
}
