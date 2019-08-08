package com.seangogo.base.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author seang
 * @date 2019/7/5 14:56
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = -7932793974645209799L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}