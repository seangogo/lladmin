package com.seangogo.modules.system.rest;

import com.seangogo.base.exception.CheckedException;
import com.seangogo.common.enums.ResultEnum;
import com.seangogo.common.utils.DataResult;
import com.seangogo.common.utils.PageResult;
import com.seangogo.modules.system.domain.FieldInfo;
import com.seangogo.modules.system.service.DataBaseService;
import com.seangogo.modules.system.service.dto.PageQueryDTO;
import com.seangogo.modules.system.service.vo.DataBaseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author seang
 * @date 2019/8/7 9:28
 */
@RestController
@RequestMapping("generator")
@Slf4j
public class GeneratorController {

    @Autowired
    private DataBaseService dataBaseService;

    @Value("${generator.enabled}")
    private Boolean generatorEnabled;

    /**
     * 获取数据库-表-字段 树
     */
    @GetMapping("/tree")
    @PreAuthorize("hasRole('cjyly')")
    public DataResult getTree() {
        return dataBaseService.getTree();
    }

    /**
     * 通过数据库设置获取所有表名称
     */
    @PostMapping("/create/database")
    public DataResult getAllTables(@RequestBody DataBaseDto dto) {
        return dataBaseService.createTable(dto);
    }

    /**
     * 分页查询
     *
     * @param pageable 分页构造器
     * @param dto      查询参数构造器
     */
    @GetMapping(value = "/table/page")
    @PreAuthorize("hasAuthority('generator')")
    public PageResult searchPage(@PageableDefault(sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable,
                                            PageQueryDTO.TableQueryDto dto) {
        return dataBaseService.page(pageable, dto);
    }

    /**
     * 生成代码
     * @param fields
     * @return
     */
    @PostMapping(value = "/code")
    public void generator(@RequestBody List<FieldInfo> fields, @RequestParam String tableName, HttpServletResponse response, HttpServletRequest request) throws IOException {
        if(!generatorEnabled){
            throw new CheckedException(ResultEnum.ENVIRONMENT_NOT_ALLOW_GENERATION);
        }
        dataBaseService.generator(fields,tableName,response,request);
    }

}
