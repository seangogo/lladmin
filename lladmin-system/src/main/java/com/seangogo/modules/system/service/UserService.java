package com.seangogo.modules.system.service;

import com.seangogo.base.jpa.BaseService;
import com.seangogo.modules.system.domain.Resource;
import com.seangogo.modules.system.domain.User;
import com.seangogo.modules.system.service.vo.AuthInfo;
import com.seangogo.modules.system.service.vo.MenuInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


/**
 * 用户业务层
 *
 * @author seang
 * @date 2019/7/3 11:34
 */
@CacheConfig(cacheNames = "user")
public interface UserService extends BaseService<User, Long> {
    /**
     * findByName
     *
     * @param userName
     * @return
     */
    @Cacheable(key = "'loadUserByUsername:'+#p0")
    User findByName(String userName);

    /**
     * findUserInfo
     *
     * @param userName
     * @return
     */
    @Cacheable(key = "'findUserInfo:'+#p0")
    AuthInfo findUserInfo(String userName);

    MenuInfo getAuthInfo(List<Resource> resources, Resource parent);

}
