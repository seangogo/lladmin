package com.custom.sean.common.utils.vo.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author seangogo
 */
@ConfigurationProperties(prefix = "oauth2")
@Getter
@Setter
public class SecurityProperties {
    /**
     * 浏览器环境配置
     */
    private RbacInitProperties init = new RbacInitProperties();
    /**
     * OAuth2认证服务器配置
     */
    private JwtProperties jwt = new JwtProperties();
}

