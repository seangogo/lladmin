package com.seangogo.modules.security.service.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seangogo.common.utils.DataResult;
import com.seangogo.common.utils.StateResult;
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

/**
 * 登录失败的处理器
 *
 * @author seangogo
 */
@Component("authenctiationFailureHandler")
public class AuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

//    @Autowired
//    private ConcurrentHashMap<String, Integer> badCredentialsUsers;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException {
        logger.info("登录失败:{}", e.getMessage());
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
//        Map<String, String> result = new HashMap(2);
//        result.put("status", "error");
//        result.put("msg", excepti);
//        try {
//            if (exception.getMessage().equals("Bad credentials")) {
//                String username=request.getParameter("username");
////                if (badCredentialsUsers.containsKey(username)) {
////                    int count = 10-badCredentialsUsers.get(username)/30;
////                    result.put("msg", "账号或密码错误，" + count + "次后，您的账户将锁定");
////                } else {
////                    result.put("msg", "您的密码错误次数已达到上限，账户已锁定,如需激活请联系管理员");
////                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        response.getWriter().write(objectMapper.writeValueAsString(StateResult.error(e)));
    }
}
