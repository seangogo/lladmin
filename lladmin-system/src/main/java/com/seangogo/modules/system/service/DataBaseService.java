package com.seangogo.modules.system.service;

import com.seangogo.base.jpa.BaseService;
import com.seangogo.common.utils.DataResult;
import com.seangogo.common.utils.PageResult;
import com.seangogo.modules.system.domain.DataBase;
import com.seangogo.modules.system.domain.FieldInfo;
import com.seangogo.modules.system.service.dto.PageQueryDTO;
import com.seangogo.modules.system.service.vo.DataBaseDto;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

    /**
     * 获取数据库-表-字段 树
     *
     * @return tree
     */
    DataResult getTree();

    /**
     * 数据库表分页
     * @param pageable 分页构造器
     * @param dto dto
     * @return 结果集
     */
    PageResult page(Pageable pageable, PageQueryDTO.TableQueryDto dto);

    /**
     * 生成代码
     * @param fields 字段信息
     * @param tableName 表名称
     * @param request
     */
    void generator(List<FieldInfo> fields, String tableName, HttpServletResponse response, HttpServletRequest request) throws IOException;
}

