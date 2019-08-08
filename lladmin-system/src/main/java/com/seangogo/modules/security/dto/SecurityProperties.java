package com.seangogo.modules.security.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author seangogo
 */
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class SecurityProperties {
    /**
     * 使用jwt时为token签名的秘钥
     */
    private String jwtSigningKey;
    /**
     * 客户端配置
     */
    private JwtClientsProperties[] clients = {};
}

