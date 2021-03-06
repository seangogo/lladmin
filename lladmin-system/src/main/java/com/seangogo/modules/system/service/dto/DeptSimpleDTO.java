package com.seangogo.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 部门数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
@Data
public class DeptSimpleDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;
}