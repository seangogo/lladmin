package com.seangogo.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seangogo.base.jpa.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

/**
 * @author sean
 * 2017/11/2.
 */
@Entity
@Table(name = "system_role")
@Getter
@Setter
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
    @Column(length = 20, unique = true, updatable = false)
    private String code;

    /**
     * 层级编码
     */
    @Column(unique = true, updatable = false)
    private String levelCode;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 角色简介
     */
    private String remark;

    /**
     * 角色的用户集合
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @ManyToMany
    @JoinTable(name = "system_roles_depts", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "dept_id", referencedColumnName = "id")})
    private Set<Dept> depts;

    /**
     * 角色拥有权限的资源集合
     */
    @ManyToMany
    @JoinTable(name = "system_roles_resources", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")})
    private Set<Resource> resources;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(super.getId(), role.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId());
    }
}
