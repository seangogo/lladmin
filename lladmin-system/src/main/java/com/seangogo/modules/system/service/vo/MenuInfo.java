package com.seangogo.modules.system.service.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author sean
 * @date 2017/12/25
 */
@Setter
@Getter
public class MenuInfo implements Serializable {

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 路由路径
     */
    private String path;

    /**
     * 是否菜单
     */
    private boolean isMenu;


    /**
     * 图标
     */
    private String icon;

    /**
     * 子菜单
     */
    private List<MenuInfo> children;

}
