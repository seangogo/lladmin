package com.custom.sean.common.repository;


import com.custom.sean.common.domain.User;
import com.custom.sean.common.utils.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author sean
 * 2017/11/2.
 */
public interface UserRepository extends BaseRepository<User,Long> {

    @Query("select new map(u.id as value,u.realName as label) from User u where u.org.levelCode like ?1%")
    List<Map<String,String>> findByOrgCodeforAccount(String code);

    /**
     * 通过组织层级编码查找组织下的所用用户数
     * @param levelCode 组织的层级编码
     * @return 用户数
     */
    @Query("select count (u.id) from User u where u.org.levelCode like ?1%")
    int findByOrgCode(String levelCode);

    /**
     * 通过账户名查找用户
     * @param username 帐户名
     * @return 用户
     */
    @Query("select u from User u where u.account.username = ?1")
    User findByAccount_Username(String username);

    /**
     * 批量删除
     * @param ids 用户ids
     * @param orgCode 组织编码
     */
    @Modifying
    @Query("update User u set u.delFlag=1 where u.id in ?1 and u.org.levelCode like ?2%")
    void batchesDelete(List<String> ids, String orgCode);

    /**
     * 通过ids查询集合
     * @param ids id集合
     * @return users
     */
    List<User> findByIdIn(List<String> ids);


    @Modifying
    @Transactional
    @Query("update User a set a.account.password=?2, a.account.expireTime=?3, a.account.active=true where a.id=?1")
    int updatePassword(Long accounId, String newPassword, Date pwdExpDay);
}
