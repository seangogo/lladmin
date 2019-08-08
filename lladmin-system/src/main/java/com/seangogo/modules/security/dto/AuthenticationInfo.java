package com.seangogo.modules.security.dto;

import com.seangogo.modules.security.JwtUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 返回token
 *
 * @author seang
 * @date 2019/7/3 11:31
 */
@Getter
@AllArgsConstructor
public class AuthenticationInfo implements Serializable {

    private final String token;

    private final JwtUser user;
}
