package com.seangogo.modules.system.service.impl;

import com.seangogo.base.exception.CheckedException;
import com.seangogo.base.jpa.BaseRepository;
import com.seangogo.base.jpa.BaseServiceImpl;
import com.seangogo.common.enums.ResultEnum;
import com.seangogo.common.utils.DataResult;
import com.seangogo.modules.system.domain.DataBase;
import com.seangogo.modules.system.domain.TableInfo;
import com.seangogo.modules.system.repository.DataBaseRepository;
import com.seangogo.modules.system.service.DataBaseService;
import com.seangogo.modules.system.service.vo.DataBaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author seang
 * @date 2019/8/7 10:49
 */
@Service
public class DataBaseServiceImpl extends BaseServiceImpl<DataBase, Long> implements DataBaseService {

    @Autowired
    private DataBaseRepository dataBaseRepository;

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
    public DataResult createTable(DataBaseDto dto) {
        List<TableInfo> list = getBaseDataTable(dto);

        return null;
    }

    private List<TableInfo> getBaseDataTable(DataBaseDto dto) {
        List<TableInfo> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dto.getJdbcUrl(), dto.getUsername(), dto.getPassword());
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW TABLES;");
            Connection conn1 = DriverManager.getConnection(dto.getJdbcUrl(), dto.getUsername(), dto.getPassword());
            Statement statement1 = conn1.createStatement();
            while (resultSet.next()) {
                TableInfo tableInfo = new TableInfo();
                //这里就获取到了数据库中的所有的表的名称了。
                tableInfo.setTableName(resultSet.getString(1));
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
        }
    }
}
