package com.seangogo.modules.system.service;

import com.seangogo.base.jpa.BaseService;
import com.seangogo.common.utils.DataResult;
import com.seangogo.modules.system.domain.Dept;
import org.springframework.cache.annotation.CacheConfig;

/**
 * @author seang
 * @date 2019/8/6 15:12
 */
@CacheConfig(cacheNames = "dept")
public interface DeptService extends BaseService<Dept, Long> {

    /**
     * 获取部门树形结构
     *
     * @param name 部门名称
     * @return 结果集
     */
    DataResult getTree(String name);
}
