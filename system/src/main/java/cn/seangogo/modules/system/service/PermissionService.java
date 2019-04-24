package cn.seangogo.modules.system.service;

import cn.seangogo.modules.system.domain.Permission;
import cn.seangogo.modules.system.service.dto.PermissionDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.Collection;
import java.util.List;

/**
 * @author jie
 * @date 2018-12-08
 */
@CacheConfig(cacheNames = "permission")
public interface PermissionService {

    /**
     * get
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    PermissionDTO findById(long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    PermissionDTO create(Permission resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Permission resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * permission tree
     * @return
     */
    @Cacheable(key = "'tree'")
    Object getPermissionTree(List<Permission> permissions);

    /**
     * findByPid
     * @param pid
     * @return
     */
    @Cacheable(key = "'pid:'+#p0")
    List<Permission> findByPid(long pid);

    /**
     * build Tree
     * @param permissionDTOS
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    Object buildTree(List<PermissionDTO> permissionDTOS);
}
