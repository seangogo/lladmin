package com.seangogo.modules.system.service.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sean
 * @date 2017/11/6
 */
@Setter
@Getter
public class AuthInfo {
    /**
     * 账户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像url
     */
    private String avatar;

    /**
     * 菜单
     */
    private MenuInfo menuInfo;

    /**
     * 按钮
     */
    private Set<String> buttons = new HashSet<>();

}
