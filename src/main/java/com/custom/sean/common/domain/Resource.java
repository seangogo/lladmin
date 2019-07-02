package com.custom.sean.common.domain;

import com.custom.sean.common.utils.jpa.BaseEntity;
import com.custom.sean.common.utils.menu.ResourceType;
import com.custom.sean.common.utils.vo.rbac.ResourceInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author sean
 * 2017/11/2.
 */
@Entity
@Table(name = "auth_resource")
@Getter
@Setter
@DynamicUpdate
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
    @NotNull
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


    public ResourceInfo roleToTree(List<Long> ids) {
        ResourceInfo result = new ResourceInfo();
        BeanUtils.copyProperties(this, result);
        List<ResourceInfo> children = new ArrayList();
        for (Resource child : getChildren()) {
            if (ids.contains(child.getId())) {
                children.add(child.roleToTree(ids));
            }
        }
        result.setChildren(children);
        return result;
    }

    public void addChild(Resource child) {
        children.add(child);
        child.setParent(this);
    }
}
