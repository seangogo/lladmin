package com.seangogo.modules.system.service;

import com.seangogo.base.jpa.BaseService;
import com.seangogo.common.utils.DataResult;
import com.seangogo.modules.system.domain.DataBase;
import com.seangogo.modules.system.service.vo.DataBaseDto;
import org.springframework.cache.annotation.CacheConfig;

/**
 * @author seang
 * @date 2019/8/7 10:48
 */
@CacheConfig(cacheNames = "dataBase")
public interface DataBaseService extends BaseService<DataBase, Long> {
    /**
     * 导入数据信息
     *
     * @param dto dto
     * @return success
     */
    DataResult createTable(DataBaseDto dto);
}

