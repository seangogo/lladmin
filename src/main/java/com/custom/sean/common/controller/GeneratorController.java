package com.custom.sean.common.controller;

import com.custom.sean.common.exception.CheckedException;
import com.custom.sean.common.utils.generator.CodeGeneratorTool;
import com.custom.sean.common.utils.generator.FreemarkerTool;
import com.custom.sean.common.utils.generator.entity.ClassInfo;
import com.custom.sean.common.utils.generator.entity.ReturnT;
import com.custom.sean.common.utils.vo.DataResult;
import com.custom.sean.common.utils.vo.ResultEnum;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成
 *
 * @author seang
 */
@RestController
@RequestMapping("/auth/generator")
@Slf4j
public class GeneratorController {

    @Resource
    private FreemarkerTool freemarkerTool;

    @RequestMapping("/genCode")
    @ResponseBody
    public ReturnT<Map<String, String>> codeGenerate(String tableSql, String authorName, String packageName) {

        if (StringUtils.isBlank(authorName)) authorName = "大狼狗";

        if (StringUtils.isBlank(packageName)) packageName = "com.custom.sean.common.test";

        try {

            if (StringUtils.isBlank(tableSql)) {
                return new ReturnT<>(ReturnT.FAIL_CODE, "表结构信息不可为空");
            }

            // parse table
            ClassInfo classInfo = CodeGeneratorTool.processTableIntoClassInfo(tableSql);

            // code genarete
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("classInfo", classInfo);
            params.put("authorName", authorName);
            params.put("packageName", packageName);

            // result
            Map<String, String> result = new HashMap<String, String>();

            //UI
            result.put("swaggerui", freemarkerTool.processString("xxl-code-generator/swagger-ui.ftl", params));
            result.put("elementui", freemarkerTool.processString("xxl-code-generator/element-ui.ftl", params));
            result.put("bootstrap", freemarkerTool.processString("xxl-code-generator/bootstrap.ftl", params));

            //mybatis old
            result.put("controller", freemarkerTool.processString("xxl-code-generator/controller.ftl", params));
            result.put("service", freemarkerTool.processString("xxl-code-generator/service.ftl", params));
            result.put("service_impl", freemarkerTool.processString("xxl-code-generator/service_impl.ftl", params));
            result.put("dao", freemarkerTool.processString("xxl-code-generator/dao.ftl", params));
            result.put("mybatis", freemarkerTool.processString("xxl-code-generator/mybatis.ftl", params));
            result.put("model", freemarkerTool.processString("xxl-code-generator/model.ftl", params));
            //jpa
            result.put("entity", freemarkerTool.processString("xxl-code-generator/entity.ftl", params));
            result.put("repository", freemarkerTool.processString("xxl-code-generator/repository.ftl", params));
            result.put("jpacontroller", freemarkerTool.processString("xxl-code-generator/jpacontroller.ftl", params));
            //jdbc template
            result.put("jtdao", freemarkerTool.processString("xxl-code-generator/jtdao.ftl", params));
            result.put("jtdaoimpl", freemarkerTool.processString("xxl-code-generator/jtdaoimpl.ftl", params));
            //beetsql
            result.put("beetlmd", freemarkerTool.processString("xxl-code-generator/beetlmd.ftl", params));
            result.put("beetlentity", freemarkerTool.processString("xxl-code-generator/beetlentity.ftl", params));
            result.put("beetlcontroller", freemarkerTool.processString("xxl-code-generator/beetlcontroller.ftl", params));
            //mybatis plus
            result.put("pluscontroller", freemarkerTool.processString("xxl-code-generator/pluscontroller.ftl", params));
            result.put("plusmapper", freemarkerTool.processString("xxl-code-generator/plusmapper.ftl", params));

            // 计算,生成代码行数
            int lineNum = 0;
            for (Map.Entry<String, String> item : result.entrySet()) {
                if (item.getValue() != null) {
                    lineNum += StringUtils.countMatches(item.getValue(), "\n");
                }
            }
            log.info("生成代码行数：{}", lineNum);
            //测试环境可自行开启
            //logger.info("生成代码数据：{}", result);
            return new ReturnT<>(result);
        } catch (IOException | TemplateException e) {
            log.error(e.getMessage(), e);
            return new ReturnT<>(ReturnT.FAIL_CODE, "表结构解析失败" + e.getMessage());
        }
    }

    /**
     * 通过数据库设置获取所有表名称
     */
    @RequestMapping("/tables")
    public DataResult getAllTables(String jdbcUrl, String username, String password) {
        Map<String,String> map=new HashMap<>();
        try {
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW TABLES;");
            Connection conn1 = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement1 = conn1.createStatement();
            while (resultSet.next()) {
                //这里就获取到了数据库中的所有的表的名称了。
                String tableName = resultSet.getString(1);
                ResultSet result = statement1.executeQuery("show CREATE TABLE "+tableName);
                if (result.next()){
                    map.put(tableName,result.getString(2));
                }
            }
            return DataResult.success(map);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CheckedException(ResultEnum.DATA_NOT_EXIST);
        }
    }
}
