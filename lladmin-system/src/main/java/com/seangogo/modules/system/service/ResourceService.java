package com.seangogo.modules.system.service;

import com.seangogo.base.jpa.BaseService;
import com.seangogo.modules.system.domain.Resource;
import com.seangogo.modules.system.domain.User;
import com.seangogo.modules.system.service.dto.ResourceDTO;
import com.seangogo.modules.system.service.vo.ResourceDto;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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

    /**
     * 创建资源
     *
     * @param resource vo
     */
    @CacheEvict(value = "resource", allEntries = true)
    void create(ResourceDto resource);

    /**
     * 修改资源
     *
     * @param id  唯一标识
     * @param dto dto
     */
    @CacheEvict(value = "resource", allEntries = true)
    void update(Long id, ResourceDto dto);
}
