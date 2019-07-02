package com.custom.sean.common.repository;

import com.custom.sean.common.domain.Role;
import com.custom.sean.common.domain.User;
import com.custom.sean.common.utils.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author sean
 * @date 2017/11/6
 */
public interface RoleRepository extends BaseRepository<Role,Long> {

    @Query("select new map(r.id as value,r.name as label) from Role r where r.code <> 'Sysadmin' and r.createdBy = ?1 ")
    List<Map<String,String>> findLabel(String username);

    @Query("select new map(r.id as value,r.name as label) from Role r where r.code <> ?1 ")
    List<Map<String,String>> findSuperLabel(String username);

    /**
     * 根据角色编码查询角色
     * @param code
     * @return
     */
    Role findByCode(String code);

    List<Role> findByLevelCodeLikeOrderByCreatedDate(String levelCode);

    List<Role> findByIdIn(String[] ids);

    /**
     * 查找role集合下是否有用户
     * @param ids 角色ids
     * @return 用户数
     */
    @Query("select r.users from Role r where r.id in ?1")
    List<User> findUserCountByRoleIds(List<String> ids);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIdIn(List<String> ids);

}
