package com.seangogo.modules.system.domain.enums;

import lombok.Getter;

/**
 * @author sean
 * @date 2017/9/11
 */
@Getter
public enum ResourceType {
    /**
     * 菜单
     */
    MENU,
    /**
     * 按钮
     */
    BUTTON,
    /**
     * 路由
     */
    ROUTE,
    /**
     * 根节点
     */
    ROOT
}