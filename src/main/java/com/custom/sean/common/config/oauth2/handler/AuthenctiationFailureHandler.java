package com.custom.sean.common.config.oauth2.handler;

import com.custom.sean.common.annotation.SystemControllerLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 浏览器环境下登录失败的处理器
 *
 * @author seangogo
 */
@Component("authenctiationFailureHandler")
public class AuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ConcurrentHashMap<String, Integer> badCredentialsUsers;

    @Override
    @SystemControllerLog(description = "登陆失败")
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败:{}", exception.getMessage());
        response.setStatus(HttpStatus.OK.value());
        Map<String, String> result = new HashMap(2);
        response.setContentType("application/json;charset=UTF-8");
        result.put("status", "error");
        result.put("type", "account");
        result.put("msg", exception.getClass().getSimpleName());
        try {
            if (exception.getMessage().equals("Bad credentials")) {
                String username=request.getParameter("username");
                if (badCredentialsUsers.containsKey(username)) {
                    int count = 10-badCredentialsUsers.get(username)/30;
                    result.put("msg", "账号或密码错误，" + count + "次后，您的账户将锁定");
                } else {
                    result.put("msg", "您的密码错误次数已达到上限，账户已锁定,如需激活请联系管理员");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
