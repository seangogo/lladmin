package com.seangogo.modules.system.service.vo;

import com.seangogo.modules.system.domain.enums.ResourceType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 新增/修改资源VO
 *
 * @author seang
 * @date 2019/7/18 16:22
 */
@Getter
@Setter
public class ResourceDto {

    /**
     * 资源名称
     */
    @NotBlank
    private String name;

    /**
     * 资源编码
     */
    @NotBlank
    private String code;

    /**
     * 资源类型 0=菜单 1=按钮 2=路由
     */
    @NotNull
    private ResourceType type;

    /**
     * 父节点
     */
    private Long parentId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 简介
     */
    private String remark;
}
