package com.seangogo.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seangogo.base.jpa.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 部门数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "system_dept")
public class Dept extends BaseEntity<String> {

    /**
     * 名称
     */
    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    /**
     * 上级部门
     */
    @Column(name = "pid", nullable = false)
    @NotNull
    private Long pid;

    /**
     * 可用
     */
    @NotNull
    private Boolean enabled;

    /**
     * 层级编码
     */
    @Column(unique = true)
    private String levelCode;


    @JsonIgnore
    @OneToMany(mappedBy = "dept")
    private Set<Role> roles;

    public @interface Update {
    }

}