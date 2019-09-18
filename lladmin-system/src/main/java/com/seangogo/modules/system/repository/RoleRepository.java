package com.seangogo.modules.system.repository;

import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.modules.system.domain.Resource;
import com.seangogo.modules.system.domain.Role;
import com.seangogo.modules.system.service.vo.DeptTree;
import com.seangogo.modules.system.service.vo.DeptTreeInterface;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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

    @Query("select r from Role r where r.dept.levelCode like ?1")
    List<DeptTreeInterface> findByDeptLevelCode(String leaveCode);

    /**
     * 根据层级编码查找
     *
     * @param levelCode 层级编码
     * @return 角色
     */
    List<Role> findByLevelCodeLikeOrderByCreatedTime(String levelCode);

    @EntityGraph(value = "role-resource-graph",
            type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT r FROM Role r where r in ?1")
    Set<Role> findByRoles(Set<Role> roles);

    @Query("select coalesce(SUM(1),0) from Role r where r.parentId = ?1")
    int findSumByParent(Long id);

    /**
     * 根据条件删除中间表数据
     * @param ids 资源
     * @param id 角色
     * @return 删除条数
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE  from system_roles_resources  where resource_id in ?1 and role_id=?2",nativeQuery = true)
    int deleteforResourceIdsAndRoleId(Set<Long> ids, Long id);
}
