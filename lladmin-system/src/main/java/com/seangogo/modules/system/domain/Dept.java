package com.seangogo.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seangogo.base.jpa.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
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

    @NotNull
    private Boolean enabled;

    /**
     * 上级部门
     */
    @Column(name = "pid", nullable = false)
    @NotNull
    private Long pid;

    @JsonIgnore
    @ManyToMany(mappedBy = "depts")
    private Set<Role> roles;

    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    public @interface Update {
    }
}