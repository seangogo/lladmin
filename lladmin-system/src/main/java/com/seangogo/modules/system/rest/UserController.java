package com.seangogo.modules.system.rest;

import com.seangogo.common.utils.DataResult;
import com.seangogo.modules.security.utils.JwtTokenUtil;
import com.seangogo.modules.system.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author seang
 * @date 2019/7/4 17:42
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 获取当前账户登录的用户信息
     *
     * @return 菜单 按钮权限
     */
    @GetMapping("/info")
    @PreAuthorize("isAuthenticated()")
    public DataResult authenticationInfo() {
        return DataResult.success(userService.findUserInfo(jwtTokenUtil.getUserName()));
    }

}
