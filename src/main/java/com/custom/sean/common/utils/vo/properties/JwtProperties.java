package com.custom.sean.common.utils.vo.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtProperties {

    /**
     * 使用jwt时为token签名的秘钥
     */
    private String jwtSigningKey;
    /**
     * 客户端配置
     */
    private JwtClientsProperties[] clients = {};

}