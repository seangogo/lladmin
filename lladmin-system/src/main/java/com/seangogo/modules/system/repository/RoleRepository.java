package com.seangogo.modules.system.repository;

import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.modules.system.domain.Role;

import java.util.List;
import java.util.Set;

/**
 * 角色数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:34
 */
public interface RoleRepository extends BaseRepository<Role, Long> {

    /**
     * 通过用户主键查找所有角色
     *
     * @param id 用户主键
     * @return 角色集合
     */
    Set<Role> findByUsers_Id(Long id);

    /**
     * 通过编码查找角色
     *
     * @param code 编码
     * @return 角色
     */
    Role findByCode(String code);

    /**
     * 根据层级编码查找
     *
     * @param levelCode 层级编码
     * @return 角色
     */
    List<Role> findByLevelCodeLikeOrderByCreatedTime(String levelCode);
}
