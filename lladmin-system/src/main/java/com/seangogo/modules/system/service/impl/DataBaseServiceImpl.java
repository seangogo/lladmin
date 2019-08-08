package com.seangogo.modules.system.service.impl;

import com.seangogo.base.exception.CheckedException;
import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.base.jpa.BaseServiceImpl;
import com.seangogo.common.enums.ResultEnum;
import com.seangogo.common.utils.DataResult;
import com.seangogo.modules.system.domain.DataBase;
import com.seangogo.modules.system.domain.FieldInfo;
import com.seangogo.modules.system.domain.TableInfo;
import com.seangogo.modules.system.repository.DataBaseRepository;
import com.seangogo.modules.system.repository.FieldInfoRepository;
import com.seangogo.modules.system.repository.TableInfoRepository;
import com.seangogo.modules.system.service.DataBaseService;
import com.seangogo.modules.system.service.vo.DataBaseDto;
import com.seangogo.modules.system.service.vo.DataBaseTreeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional
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
        DataBaseTreeDto root = new DataBaseTreeDto(0L, "根节点");
        for (DataBase basis : bases) {
            DataBaseTreeDto b = new DataBaseTreeDto(basis.getId(), basis.getAlias());
            for (TableInfo tableInfo : basis.getTables()) {
                DataBaseTreeDto t = new DataBaseTreeDto(tableInfo.getId(), tableInfo.getTableName());
                t.setChildren(tableInfo.getFields().stream().map(f -> new DataBaseTreeDto(f.getId(), f.getColumnName())).collect(Collectors.toList()));
                b.getChildren().add(t);
            }
            root.getChildren().add(b);
        }
        return DataResult.success(root);
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
}
