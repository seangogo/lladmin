package com.custom.sean.common.utils.vo.rbac;

import com.custom.sean.common.utils.menu.ResourceType;
import lombok.Getter;
import lombok.Setter;

/**
 * 新增/修改资源VO
 * @author sean
 * @date 2017/11/7
 */
@Getter
@Setter
public class ResourceVo {

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源编码
     */
    private String code;

    /**
     * 资源类型 0=菜单 1=按钮
     */
    private ResourceType type;

    //@Range(min = 36,max = 36,message = "数据有误")
    private Long parentId;

    /**
     * 图标样式
     */
    private String icon;

    /**
     * 简介
     */
    private String remark;

}
