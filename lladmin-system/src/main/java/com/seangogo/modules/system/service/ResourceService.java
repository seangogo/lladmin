package com.seangogo.modules.system.service;

import com.seangogo.base.jpa.BaseService;
import com.seangogo.common.utils.DataResult;
import com.seangogo.modules.system.domain.Resource;
import com.seangogo.modules.system.domain.User;
import com.seangogo.modules.system.service.dto.ResourceDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author seang
 * @date 2019/7/5 14:37
 */
@CacheConfig(cacheNames = "resource")
public interface ResourceService extends BaseService<Resource, Long> {
    @Cacheable(key = "'userToGrantedAuthorities:' + #p0.username")
    Collection<GrantedAuthority> userToGrantedAuthorities(User user);

    /**
     * 获取所有资源树
     *
     * @return 结果集
     */
    @Cacheable(key = "'getAllTree:' + #p0")
    ResourceDTO getAllTree(String name);

}
