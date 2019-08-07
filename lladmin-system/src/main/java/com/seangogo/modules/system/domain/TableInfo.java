package com.seangogo.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seangogo.base.jpa.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
    @Column(length = 4000)
    private String createSql;

    /**
     * 所属数据库
     **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "database_id")
    private DataBase dataBase;

    @OneToMany(mappedBy = "tableInfo")
    @JsonIgnore
    private Set<FieldInfo> fields;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TableInfo)) {
            return false;
        }
        return super.getId() != null && super.getId().equals(((TableInfo) obj).getId());
    }

    @Override
    public int hashCode() {
        return 2023;
    }
}
