package com.seangogo.modules.system.domain;

import com.seangogo.base.jpa.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
public class FieldInfo extends BaseEntity<String> {
    private String columnName;
    private String fieldName;
    private String fieldClass;
    private String fieldComment;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableInfo tableInfo;
}
