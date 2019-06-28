package com.custom.sean.common.domain;

import com.custom.sean.common.utils.jpa.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "auth_dict")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
@NoArgsConstructor

public class Dict extends BaseEntity<String> {

    /**
     * 字典名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 唯一编码
     */
    @Column(unique = true)
    private String code;

    /**
     * 字典值
     */
    @Column(name = "value", length = 20)
    private String dbValue;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 层级编码
     */
    private String levelCode;

    /**
     * 备注
     */
    @Column(length = 100)
    private String remark;


    @Transient
    private List<Dict> children;

    @Transient
    private String value;

    @Transient
    private String title;

    public String getValue() {
        return this.id;
    }

    public String getTitle() {
        return this.name;
    }

    public void addChild(Dict child) {
        children.add(child);
    }

    public Dict(String name, String code, String parentId, String levelCode, String remark,
                String createdBy, Date createDate, int delFlag, String id, int version, String updateBy, Date updatedDate) {
        this.name = name;
        this.code = code;
        this.parentId = parentId;
        this.levelCode = levelCode;
        this.remark = remark;
        this.setCreatedBy(createdBy);
        this.setCreatedDate(createDate);
        this.setDelFlag(delFlag);
        this.setId(id);
        this.setVersion(version);
        this.setUpdateBy(updateBy);
        this.setUpdatedDate(updatedDate);
    }
    public Dict(String name, String code, String parentId, String levelCode, String remark) {
        this.name = name;
        this.code = code;
        this.parentId = parentId;
        this.levelCode = levelCode;
        this.remark = remark;
    }
}
