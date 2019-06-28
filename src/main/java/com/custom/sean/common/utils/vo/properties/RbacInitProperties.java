package com.custom.sean.common.utils.vo.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RbacInitProperties {
    /**
     * 超级管理员角色的code
     */
    private String sysadmin;

    /**
     * 超级管理员角色的初始密码
     */
    private String password;
}