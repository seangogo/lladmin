package com.seangogo.modules.system.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.seangogo.base.exception.CheckedException;
import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.base.jpa.BaseServiceImpl;
import com.seangogo.common.enums.ResultEnum;
import com.seangogo.common.utils.ColUtil;
import com.seangogo.common.utils.DataResult;
import com.seangogo.common.utils.PageResult;
import com.seangogo.common.utils.StringUtils;
import com.seangogo.modules.system.domain.DataBase;
import com.seangogo.modules.system.domain.FieldInfo;
import com.seangogo.modules.system.domain.GenConfig;
import com.seangogo.modules.system.domain.TableInfo;
import com.seangogo.modules.system.repository.DataBaseRepository;
import com.seangogo.modules.system.repository.FieldInfoRepository;
import com.seangogo.modules.system.repository.GenConfigRepository;
import com.seangogo.modules.system.repository.TableInfoRepository;
import com.seangogo.modules.system.service.DataBaseService;
import com.seangogo.modules.system.service.dto.PageQueryDTO;
import com.seangogo.modules.system.service.dto.TableInfoDto;
import com.seangogo.modules.system.service.vo.DataBaseDto;
import com.seangogo.modules.system.service.vo.DataBaseTreeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.seangogo.modules.security.utils.GenUtil.*;

/**
 * @author seang
 * @date 2019/8/7 10:49
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DataBaseServiceImpl extends BaseServiceImpl<DataBase, Long> implements DataBaseService {

    @Autowired
    private DataBaseRepository dataBaseRepository;

    @Autowired
    private TableInfoRepository tableInfoRepository;

    @Autowired
    private FieldInfoRepository fieldInfoRepository;

    @Autowired
    private GenConfigRepository genConfigRepository;

    @Override
    public BaseRepository<DataBase, Long> getBaseDao() {
        return this.dataBaseRepository;
    }

    /**
     * 导入数据信息
     *
     * @param dto dto
     * @return success
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    public DataResult createTable(DataBaseDto dto) {
        DataBase dataBase = new DataBase();
        dataBase.setAlias(dto.getAlias());
        dataBase.setJdbcUrl(dto.getJdbcUrl());
        dataBase.setName(dto.getAlias());
        dataBase.setUsername(dto.getUsername());
        dataBase.setPassword(dto.getPassword());
        dataBaseRepository.saveAndFlush(dataBase);
        List<TableInfo> list = getBaseDataTable(dto, dataBase);
        tableInfoRepository.saveAll(list);
        List<FieldInfo> fields = new ArrayList<>();
        for (TableInfo tableInfo : list) {
            fields.addAll(getTableInfo(dto, tableInfo));
        }
        fieldInfoRepository.saveAll(fields);
        return DataResult.success(dataBaseRepository.findAll());

    }

    /**
     * 获取数据库-表-字段 树
     *
     * @return tree
     */
    @Override
    public DataResult getTree() {
        List<DataBase> bases = dataBaseRepository.findTree();
        DataBaseTreeDto root = new DataBaseTreeDto("0", "根节点",new ArrayList<>());
        for (DataBase basis : bases) {
            DataBaseTreeDto b = new DataBaseTreeDto("d"+basis.getId(), basis.getAlias(),new ArrayList<>());
            for (TableInfo tableInfo : basis.getTables()) {
                DataBaseTreeDto t = new DataBaseTreeDto("t"+tableInfo.getId(), tableInfo.getTableName());
                t.setChildren(tableInfo.getFields().stream().map(f -> new DataBaseTreeDto("f"+f.getId(), f.getColumnName())).collect(Collectors.toList()));
                b.getChildren().add(t);
            }
            root.getChildren().add(b);
        }
        return DataResult.success(root);
    }

    /**
     * 数据库表分页
     *
     * @param pageable 分页构造器
     * @param dto      dto
     * @return 结果集
     */
    @Override
    public PageResult page(Pageable pageable, PageQueryDTO.TableQueryDto dto) {
        Specification<TableInfoDto> querySpecifi = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (null != dto.getTableName()) {
                predicates.add(criteriaBuilder.like(root.get("tableName").as(String.class), "%" + dto.getTableName() + "%"));
            }
            if (null != dto.getDataBaseId()) {
                predicates.add(criteriaBuilder.equal(root.get("dataBase").get("id").as(Long.class), dto.getDataBaseId()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Page<TableInfoDto> page = tableInfoRepository.findAll(querySpecifi, pageable);
        return PageResult.getPageVo(page);
    }

    private List<TableInfo> getBaseDataTable(DataBaseDto dto, DataBase dataBase) {
        List<TableInfo> list = new ArrayList<>();
        Connection conn = null;
        Connection conn1 = null;
        try {
            conn = DriverManager.getConnection(dto.getJdbcUrl(), dto.getUsername(), dto.getPassword());
            Statement statement = conn.createStatement();
            log.info("数据库名称:{}", conn.getSchema());
            String sql = "select table_name ,create_time , engine, table_collation, table_comment " +
                    "from information_schema.tables where table_schema = (select database()) order by create_time desc";
            ResultSet resultSet = statement.executeQuery(sql);
            conn1 = DriverManager.getConnection(dto.getJdbcUrl(), dto.getUsername(), dto.getPassword());
            Statement statement1 = conn1.createStatement();
            while (resultSet.next()) {
                TableInfo tableInfo = new TableInfo();
                //这里就获取到了数据库中的所有的表的名称了。
                tableInfo.setTableName(resultSet.getString(1));
                tableInfo.setCreateTime(resultSet.getTimestamp(2));
                tableInfo.setEngine(resultSet.getString(3));
                tableInfo.setCoding(resultSet.getString(4));
                tableInfo.setRemark(resultSet.getString(5));
                tableInfo.setDataBase(dataBase);
                ResultSet result = statement1.executeQuery("show CREATE TABLE " + tableInfo.getTableName());
                if (result.next()) {
                    tableInfo.setCreateSql(result.getString(2));
                }
                list.add(tableInfo);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CheckedException(ResultEnum.DATA_NOT_EXIST);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (conn1 != null) {
                    conn1.close();
                }
            } catch (SQLException e) {
                log.info("Problem closing connection" + e);
            }
        }
    }

    private List<FieldInfo> getTableInfo(DataBaseDto dto, TableInfo table) {
        List<FieldInfo> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dto.getJdbcUrl(), dto.getUsername(), dto.getPassword());
            String sql = "select column_name, is_nullable, data_type, column_comment, column_key, extra from information_schema.columns " +
                    "where table_name = '" + table.getTableName() + "' and table_schema = (select database()) order by ordinal_position";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                FieldInfo fieldInfo = new FieldInfo();
                fieldInfo.setColumnName(resultSet.getString(1));
                fieldInfo.setNullable("YES".equals(resultSet.getString(2)));
                fieldInfo.setFieldType(resultSet.getString(3));
                fieldInfo.setColumnComment(resultSet.getString(4));
                fieldInfo.setColumnKey(resultSet.getString(5));
                fieldInfo.setExtra(resultSet.getString(6));
                fieldInfo.setTableInfo(table);
                list.add(fieldInfo);
            }
            return list;
        } catch (SQLException e) {
            log.info(e.getMessage());
            e.printStackTrace();
            throw new CheckedException(ResultEnum.DATA_NOT_EXIST);
        }
    }

    /**
     * 生成代码
     * @param fields 字段信息
     * @param tableName 表名称
     * @param response
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void generator(List<FieldInfo> fields, String tableName, HttpServletResponse response,HttpServletRequest request) throws IOException {
        GenConfig genConfig = genConfigRepository.findById(1L).get();
        Map<String,Object> map = new HashMap();
        map.put("package",genConfig.getPack());
        map.put("moduleName",genConfig.getModuleName());
        map.put("author",genConfig.getAuthor());
        map.put("date", LocalDate.now().toString());
        map.put("tableName",tableName);
        String className = StringUtils.toCapitalizeCamelCase(tableName);
        String changeClassName = StringUtils.toCamelCase(tableName);
        // 判断是否去除表前缀
        if (StringUtils.isNotEmpty(genConfig.getPrefix())) {
            className = StringUtils.toCapitalizeCamelCase(StrUtil.removePrefix(tableName,genConfig.getPrefix()));
            changeClassName = StringUtils.toCamelCase(StrUtil.removePrefix(tableName,genConfig.getPrefix()));
        }
        map.put("className", className);
        map.put("upperCaseClassName", className.toUpperCase());
        map.put("changeClassName", changeClassName);
        map.put("hasTimestamp",false);
        map.put("hasBigDecimal",false);
        map.put("hasQuery",false);
        map.put("auto",false);

        List<Map<String,Object>> columns = new ArrayList<>();
        List<Map<String,Object>> queryColumns = new ArrayList<>();
        for (FieldInfo column : fields) {
            Map<String,Object> listMap = new HashMap();
            listMap.put("columnComment",column.getColumnComment());
            listMap.put("columnKey",column.getColumnKey());

            String colType = ColUtil.cloToJava(column.getFieldType());
            String changeColumnName = StringUtils.toCamelCase(column.getColumnName());
            String capitalColumnName = StringUtils.toCapitalizeCamelCase(column.getColumnName());
            if(PK.equals(column.getColumnKey())){
                map.put("pkColumnType",colType);
                map.put("pkChangeColName",changeColumnName);
                map.put("pkCapitalColName",capitalColumnName);
            }
            if(TIMESTAMP.equals(colType)){
                map.put("hasTimestamp",true);
            }
            if(BIGDECIMAL.equals(colType)){
                map.put("hasBigDecimal",true);
            }
            if(EXTRA.equals(column.getExtra())){
                map.put("auto",true);
            }
            listMap.put("columnType",colType);
            listMap.put("columnName",column.getColumnName());
            listMap.put("isNullable",column.isNullable()?"0":"1");
            listMap.put("columnShow",column.isColumnShow()?"0":"1");
            listMap.put("changeColumnName",changeColumnName);
            listMap.put("capitalColumnName",capitalColumnName);

            // 判断是否有查询，如有则把查询的字段set进columnQuery
            if(!StringUtils.isBlank(column.getColumnQuery())){
                listMap.put("columnQuery",column.getColumnQuery());
                map.put("hasQuery",true);
                queryColumns.add(listMap);
            }
            columns.add(listMap);
        }
        map.put("columns",columns);
        map.put("queryColumns",queryColumns);
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));

        // 生成后端代码
        List<String> templates = getAdminTemplateNames();
        for (String templateName : templates) {
            Template template = engine.getTemplate("generator/admin/"+templateName+".ftl");
            String filePath = getAdminFilePath(templateName,genConfig,className);

            File file = new File(filePath);

            // 如果非覆盖生成
            if(!genConfig.getCover() && FileUtil.exist(file)){
                continue;
            }
            // 生成代码
            genFile(file,template, map);
        }

        // 生成前端代码
//        templates = getFrontTemplateNames();
//        for (String templateName : templates) {
//            Template template = engine.getTemplate("generator/front/"+templateName+".ftl");
//            String filePath = getFrontFilePath(templateName,genConfig,map.get("changeClassName").toString());
//
//            File file = new File(filePath);
//
//            // 如果非覆盖生成
//            if(!genConfig.getCover() && FileUtil.exist(file)){
//                continue;
//            }
//            // 生成代码
//            genFile(file, template, map);
//        }
        String filePath = System.getProperty("user.dir") + File.separator + genConfig.getModuleName();
        File zip=ZipUtil.zip(filePath, filePath+".zip");
        //进行浏览器下载
        //获得浏览器代理信息
        final String userAgent = request.getHeader("USER-AGENT");
        //判断浏览器代理并分别设置响应给浏览器的编码格式
        String finalFileName = URLEncoder.encode("lladmin-generate.zip","UTF-8");
        response.setContentType("application/x-octet-stream");
        //下载文件的名称
        response.setHeader("Content-Disposition" ,"attachment;filename=" +finalFileName);
        ServletOutputStream servletOutputStream=response.getOutputStream();
        DataOutputStream temps = new DataOutputStream(servletOutputStream);
        DataInputStream in = new DataInputStream(new FileInputStream(filePath+".zip"));
        byte[] b = new byte[2048];
        try {
            while ((in.read(b)) != -1) {
                temps.write(b);
            }
            temps.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(temps!=null) temps.close();
            if(in!=null) in.close();
            if(zip!=null) zip.delete();//删除服务器本地产生的临时压缩文件
            FileUtil.del(filePath);
            servletOutputStream.close();
        }
    }
}
