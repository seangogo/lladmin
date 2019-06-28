package com.custom.sean.common.domain;

import com.custom.sean.common.utils.jpa.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sean
 * 2017/11/2.
 */
@Entity
@Table(name = "auth_role")
@Getter
@Setter
@DynamicUpdate
public class Role extends BaseEntity<String> {

    /**
     * 角色名称
     */
    @Column(length = 20, nullable = false)
    @NotNull
    private String name;


    /**
     * 角色编码
     */
    @Column(length = 20, unique = true,updatable = false)
    private String code;

    /**
     * 层级编码
     */
    @Column(unique = true,updatable = false)
    private String levelCode;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 角色简介
     */
    private String remark;

    /**
     * 角色拥有权限的资源集合
     */
    @OneToMany(mappedBy = "role", cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private Set<RoleResource> resources  = new HashSet<>();

    /**
     * 角色的用户集合
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<RoleUser> users = new HashSet<>();
}
