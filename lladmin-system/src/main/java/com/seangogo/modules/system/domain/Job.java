package com.seangogo.modules.system.domain;

import com.seangogo.base.jpa.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 部门数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "system_job")
public class Job extends BaseEntity<String> {

    /**
     * 名称
     */
    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(unique = true)
    @NotNull
    private Long sort;

    /**
     * 状态
     */
    @Column(name = "enabled", nullable = false)
    @NotNull
    private Boolean enabled;

    @OneToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;

    public @interface Update {
    }
}