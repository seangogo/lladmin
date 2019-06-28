package com.custom.sean.common.config.oauth2.server;


import com.custom.sean.common.config.oauth2.authentication.FormAuthenticationConfig;
import com.custom.sean.common.config.oauth2.handler.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author sean
 * @date 2018/2/23
 */
@Configuration
@EnableResourceServer
public class SecurityResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        formAuthenticationConfig.configure(http);
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers( "/oauth/**", "/auth/active/**","/"
                        ,"/static/**","/**.**", "/druid/**", "/admin/health",
                        "/auth/**/isUse/**", "/auth/common/**", "/auth/account/sendCode","/auth/generator/**",
                        "/auth/user/resetPassword").permitAll()
                .anyRequest().authenticated();

    }
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("auth");
    }

}
