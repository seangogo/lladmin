package com.seangogo.modules.security.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 认证服务器注册的第三方应用配置项
 *
 * @author sean
 */
@Getter
@Setter
public class JwtClientsProperties {

    /**
     * 第三方应用appId
     */
    private String clientId;
    /**
     * 第三方应用appSecret
     */
    private String clientSecret;
    /**
     * 针对此应用发出的token的有效时间
     */
    private int accessTokenValidateSeconds;

    /**
     * 针对此应用发出的refreshToken的有效时间
     */
    private int accessTokenRefreshSeconds;

}