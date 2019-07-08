package com.seangogo.modules.system.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 部门数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
@Data
@NoArgsConstructor
public class JobDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    private Long sort;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态
     */
    private Boolean enabled;

    private DeptDTO dept;

    /**
     * 如果分公司存在相同部门，则显示上级部门名称
     */
    private String deptSuperiorName;

    /**
     * 创建日期
     */
    private Timestamp createTime;

    public JobDTO(String name, Boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }
}