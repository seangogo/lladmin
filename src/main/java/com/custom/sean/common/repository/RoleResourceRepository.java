package com.custom.sean.common.repository;

import com.custom.sean.common.domain.Resource;
import com.custom.sean.common.domain.Role;
import com.custom.sean.common.domain.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sean
 * @date 2017/11/6
 */
public interface RoleResourceRepository extends JpaRepository<RoleResource, String>, JpaSpecificationExecutor<RoleResource> {

    @Query("select r.resource.id from RoleResource r where r.role.id=?1")
    List<String> findIdsByRid(String id);

    @Modifying
    @Transactional
    @Query(value = "DELETE  from RoleResource rr where rr.resource.id in ?1 and rr.role.id=?2")
    int deleteforResourceIdsAndRoleId(Set<String> ids, String id);


    /**
     * 通过资源id删除关联数据
     * @param id 资源id
     */
   // void deleteByResource_Id(String id);

    /**
     * 清空角色和资源的关联
     *
     * @param id roleId
     */
    void deleteByRole_Id(String id);

    @Query("select rr.resource from RoleResource rr where rr.role.id=?1")
    List<Resource> findResourceByRole_Id(String roleId);


    /**
     * 通过角色查找用户权限
     * @param roles 用户对应角色
     * @return 用户菜单
     */
    @Query("SELECT distinct rr.resource from RoleResource rr where rr.role.code in ?1 and rr.resource.type <> 1 and rr.resource.sort <> 0 order by rr.resource.sort")
    List<Resource> findMenuByRoles(Set<String> roles);

    /**
     * 通过角色查找用户权限
     * @param roles 用户对应角色
     * @return 所有权限字符串
     */
    @Query("SELECT distinct rr.resource.code from RoleResource rr where rr.role.code in ?1 and rr.resource.type <> 2")
    Set<String> findButtonByRoles(Set<String> roles);


    /**
     * 用户详情
     *
     * @param role 用户对应角色
     * @return 角色所有资源名称
     */
    @Query("SELECT rr.resource.name from RoleResource rr where rr.role in ?1 and rr.resource.type = 0")
    Set<String> findResourceNameByRole(Role role);

}
