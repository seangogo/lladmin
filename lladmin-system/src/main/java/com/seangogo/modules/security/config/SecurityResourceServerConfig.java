package com.seangogo.modules.security.config;


import com.seangogo.modules.security.authentication.FormAuthenticationConfig;
import com.seangogo.modules.security.service.handler.JwtAuthenticationEntryPoint;
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
    public void configure(HttpSecurity httpSecurity) throws Exception {
        formAuthenticationConfig.configure(httpSecurity);
        httpSecurity
                // 禁用 CSRF
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/oauth/**", "/", "/auth/vCode"
                        , "/static/**", "/**.**", "/druid/**", "/admin/health",
                        "/auth/common/**", "/auth/generator/**",
                        "/auth/user/resetPassword").permitAll()
                .anyRequest().authenticated();

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("auth");
    }

}
