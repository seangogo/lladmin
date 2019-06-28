package com.custom.sean.common.config.oauth2.authentication;


import com.custom.sean.common.config.filter.SimpleCorsFilter;
import com.custom.sean.common.config.oauth2.server.TokenJwtEnhancer;
import com.custom.sean.common.utils.vo.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


/**
 * @author sean
 */
@Configuration
public class TokenStoreConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @return TokenStore
     */
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 密签
     *
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("opt");
        //final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "mypass".toCharArray());
        //converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
        return converter;
    }

    /**
     * JWT 拓展属性增强器
     *
     * @return TokenEnhancer
     */
    @Bean
    @ConditionalOnBean(TokenEnhancer.class)
    public TokenEnhancer jwtTokenEnhancer() {
        return new TokenJwtEnhancer();
    }

    /**
     * 根据配置属性设置跨域是否开启
     */
    @Configuration
    @ConditionalOnProperty(prefix = "oauth2", name = "cross", havingValue = "true")
    public static class CrossConfig {
        @Bean
        public SimpleCorsFilter simpleCorsFilter() {
            return new SimpleCorsFilter();
        }

    }


}
