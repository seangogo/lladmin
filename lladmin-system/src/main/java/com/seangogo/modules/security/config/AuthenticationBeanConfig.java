package com.seangogo.modules.security.config;

import com.seangogo.base.jpa.AuditorAwareImpl;
import com.seangogo.modules.security.dto.SecurityProperties;
import com.seangogo.modules.security.service.MyUserDetailsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sean
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableConfigurationProperties(SecurityProperties.class)
public class AuthenticationBeanConfig {

    /**
     * 默认密码处理器
     *
     * @return PasswordEncoder
     */
    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 默认认证器
     *
     * @return UserDetailsService
     */
    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    /**
     * 审计处理
     *
     * @return AuditorAware
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

    @Bean(name = "badCredentialsUsers")
    public ConcurrentHashMap monitorInfo() {
        return new ConcurrentHashMap<String, Integer>();
    }

}
