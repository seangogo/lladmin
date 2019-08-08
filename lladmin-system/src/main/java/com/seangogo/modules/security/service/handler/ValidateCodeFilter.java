package com.seangogo.modules.security.service.handler;

import com.seangogo.base.exception.ValidateCodeException;
import com.seangogo.tools.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆验证码过滤器
 * OncePerRequestFilter : 保证过滤器只被调用一次
 *
 * @author seang
 * @date 2019/7/5 14:58
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler failureHandler;

    private RedisService redisService;


    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        String uuid = ServletRequestUtils.getStringParameter(request.getRequest(), "uuid");
        String code = ServletRequestUtils.getStringParameter(request.getRequest(), "code");
        // 查询验证码
        String redisCode = redisService.getCodeVal(uuid);
        // 清除验证码
        redisService.delete(uuid);
        if (StringUtils.isBlank(redisCode)) {
            throw new ValidateCodeException("验证码已过期");
        }
        if (StringUtils.isBlank(code)) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (!code.equalsIgnoreCase(redisCode)) {
            throw new ValidateCodeException("验证码不匹配");
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 是一个登陆请求
        if (StringUtils.equals("/auth/login", request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                // 有异常就返回自定义失败处理器
                failureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        // 不是一个登录请求，不做校验 直接通过
        filterChain.doFilter(request, response);
    }

    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }
}
