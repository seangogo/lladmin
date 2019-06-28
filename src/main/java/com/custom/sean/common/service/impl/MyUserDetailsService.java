package com.custom.sean.common.service.impl;

import com.custom.sean.common.domain.User;
import com.custom.sean.common.exception.CheckedException;
import com.custom.sean.common.service.OrgService;
import com.custom.sean.common.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author sean
 * @date 2017/11/2
 */
@Slf4j
@Component
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private OrgService orgService;

    @Resource
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, CheckedException {
        log.info("表单登录用户名:" + username);
        User user = userService.findUserByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException("帐户名为" + username + "的用户不存在");
        }
        orgService.initByUser(user);
        return user;
    }
}
