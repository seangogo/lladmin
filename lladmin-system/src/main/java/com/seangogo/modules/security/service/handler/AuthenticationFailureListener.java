package com.seangogo.modules.security.service.handler;


import com.seangogo.modules.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登陆失败监听
 *
 * @author Shaoj 3/2/2017.
 */
@Component
@Transactional
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    protected ConcurrentHashMap<String, Integer> badCredentialsUsers;


    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
        String msg = authenticationFailureBadCredentialsEvent.getException().getMessage();
        if (Objects.equals("Bad credentials", msg)) {
            String account = authenticationFailureBadCredentialsEvent.getAuthentication().getPrincipal().toString();
            if (!badCredentialsUsers.containsKey(account)) {
                badCredentialsUsers.put(account, 30);
            } else {
                int count = badCredentialsUsers.get(account);
                if (count > 300) {
//                    User user = userRepository.findByAccount_Username(account);
//                    user.getAccount().setLocked(!user.getAccount().isLocked());
                    //        userRepository.save(user);
                    badCredentialsUsers.remove(account);
                } else {
                    badCredentialsUsers.put(account, count + 30);
                }
            }
        }
    }
}