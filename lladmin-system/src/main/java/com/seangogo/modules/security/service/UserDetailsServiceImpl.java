package com.seangogo.modules.security.service;

import com.seangogo.base.exception.CheckedException;
import com.seangogo.modules.security.JwtUser;
import com.seangogo.modules.system.domain.Dept;
import com.seangogo.modules.system.domain.Job;
import com.seangogo.modules.system.domain.User;
import com.seangogo.modules.system.service.ResourceService;
import com.seangogo.modules.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author sean
 * @date 2017/11/2
 */
@Slf4j
@Component
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private ResourceService resourceService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, CheckedException {
        log.info("表单登录用户名:" + username);
        User user = userService.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("帐户名为" + username + "的用户不存在");
        }
        // orgService.initByUser(user);
        return createJwtUser(user);
    }

    public UserDetails createJwtUser(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getAvatar(),
                user.getEmail(),
                user.getPhone(),
                Optional.ofNullable(user.getDept()).map(Dept::getLevelCode).orElse(null),
                Optional.ofNullable(user.getJob()).map(Job::getName).orElse(null),
                resourceService.userToGrantedAuthorities(user),
                user.isLocked(),
                user.getCreatedTime(),
                user.getUpdatedTime());
    }
}
