package com.seangogo.modules.system.rest;

import com.seangogo.common.utils.DataResult;
import com.seangogo.modules.system.service.DataBaseService;
import com.seangogo.modules.system.service.vo.DataBaseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
