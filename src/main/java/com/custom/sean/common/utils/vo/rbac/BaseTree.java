package com.custom.sean.common.utils.vo.rbac;


import lombok.Getter;
import lombok.Setter;

/**
 * 属性结构基础类
 * @author seang
 */
@Setter
@Getter
public class BaseTree {
    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 层级编码
     */
    private String levelCode;

    /**
     * 详情
     */
    private String remark;

    /**
     * 父资源
     */
    private String parentId;
}
