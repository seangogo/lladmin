package com.seangogo.modules.system.domain;

import com.seangogo.base.jpa.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * 表的数据信息
 *
 * @author seang
 * @date 2019/8/7 9:44
 */
@Entity
@Getter
@Setter
@Table(name = "generator_table_info")
public class TableInfo extends BaseEntity<String> {

    /**
     * 表名称
     **/
    private String tableName;

    /**
     * 表创建日期
     **/
    private Date createTime;

    /**
     * 数据库引擎
     **/
    private String engine;

    /**
     * 编码集
     **/
    private String coding;

    /**
     * 备注
     **/
    private String remark;

    /**
     * 建表sql
     **/
    private String createSql;

    /**
     * 所属数据库
     **/
    @ManyToOne
    @JoinColumn(name = "database_id")
    private DataBase dataBase;

}
