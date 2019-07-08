package com.seangogo.modules.system.repository;

import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.modules.system.domain.User;

/**
 * 用户数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:34
 */
public interface UserRepository extends BaseRepository<User, Long> {

    /**
     * 通过账户名查找
     *
     * @param username 账户名
     * @return entity
     */
    User findByUsername(String username);

    /**
     * 通过邮箱查找
     *
     * @param email 邮箱
     * @return entity
     */
    User findByEmail(String email);
}
