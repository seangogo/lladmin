package com.seangogo.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seangogo.base.jpa.BaseEntity;
import com.seangogo.modules.system.domain.enums.ResourceType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author sean
 * 2017/11/2.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "system_resource")
@Data
public class Resource extends BaseEntity<String> {

    /**
     * 资源名称 如xx菜单，xx按钮
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * 资源编码
     */
    @Column(length = 10, unique = true, updatable = false)
    private String code;

    /**
     * 层级编码
     */
    @Column(unique = true, updatable = false)
    private String levelCode;

    /**
     * 资源类型
     */
    private ResourceType type;

    /**
     * 序号
     */
    private int sort;

    /**
     * 图标
     */
    private String icon;

    /**
     * 简介
     */
    private String remark;

    /**
     * /**
     * 父资源
     */
    @ManyToOne
    private Resource parent;

    /**
     * 子资源
     */
    @OneToMany(mappedBy = "parent")
    @OrderBy("sort ASC")
    private List<Resource> children = new ArrayList<>();

    /**
     * 角色拥有权限的资源集合
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "resources")
    private Set<Role> roles;
}
