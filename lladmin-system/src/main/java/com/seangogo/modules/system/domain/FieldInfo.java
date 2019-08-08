package com.seangogo.modules.system.domain;

import com.seangogo.base.jpa.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 表字段
 *
 * @author seang
 * @date 2019/8/7 10:34
 */
@Entity
@Getter
@Setter
@Table(name = "generator_field_info")
@NamedEntityGraph(
        name = "database-table-field-graph",
        attributeNodes = @NamedAttributeNode(value = "tableInfo", subgraph = "table-subgraph"),
        subgraphs = {
                @NamedSubgraph(
                        name = "table-subgraph",
                        attributeNodes = @NamedAttributeNode("dataBase")
                )
        }
)
public class FieldInfo extends BaseEntity<String> {

    /**
     * 数据库字段名称
     **/
    private String columnName;

    /**
     * 允许空值
     **/
    private boolean isNullable;

    /**
     * 数据库字段键类型
     **/
    private String fieldType;

    /**
     * 数据库字段注释
     **/
    @Column(length = 1000)
    private String columnComment;

    /**
     * 数据库字段键类型
     **/
    private String columnKey;

    /**
     * 额外的参数
     **/
    private String extra;

    /**
     * 查询 1:模糊 2：精确
     **/
    private String columnQuery;

    /**
     * 是否在列表显示
     **/
    private String columnShow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private TableInfo tableInfo;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FieldInfo)) {
            return false;
        }
        return super.getId() != null && super.getId().equals(((FieldInfo) obj).getId());
    }

    @Override
    public int hashCode() {
        return 2024;
    }
}
