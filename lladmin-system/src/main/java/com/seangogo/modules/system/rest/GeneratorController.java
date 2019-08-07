package com.seangogo.modules.system.rest;

import com.seangogo.base.exception.CheckedException;
import com.seangogo.common.enums.ResultEnum;
import com.seangogo.common.utils.DataResult;
import com.seangogo.modules.system.service.DataBaseService;
import com.seangogo.modules.system.service.vo.DataBaseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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
     * 通过数据库设置获取所有表名称
     */
    @PostMapping("/create/database")
    public DataResult getAllTables(@RequestBody DataBaseDto dto) {
        return dataBaseService.createTable(dto);
    }
}
