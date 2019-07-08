package com.seangogo.modules.security.utils;

import cn.hutool.json.JSONObject;
import com.seangogo.base.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 部门数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
public class SecurityUtils {

    public static UserDetails getUserDetails() {
        UserDetails userDetails = null;
        try {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "登录状态过期");
        }
        return userDetails;
    }

    /**
     * 获取系统用户名称
     *
     * @return 系统用户名称
     */
    public static String getUsername() {
        Object obj = getUserDetails();
        JSONObject json = new JSONObject(obj);
        return json.get("username", String.class);
    }

    /**
     * 获取系统用户id
     *
     * @return 系统用户id
     */
    public static Long getUserId() {
        Object obj = getUserDetails();
        JSONObject json = new JSONObject(obj);
        return json.get("id", Long.class);
    }
}
