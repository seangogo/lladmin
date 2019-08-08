package com.seangogo.modules.system.service.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author seang
 * @date 2019/8/7 10:41
 */
@Getter
@Setter
public class DataBaseDto {

    /**
     * 别名
     **/
    private String alias;

    /**
     * 连接地址
     **/
    private String jdbcUrl;

    /**
     * 登陆账户
     **/
    private String username;

    /**
     * 密码
     **/
    private String password;
}
