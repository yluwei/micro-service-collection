package cn.ylw.sso.server.config.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.endpoint.TokenKeyEndpoint;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * token存储方式
 *
 * @author yanluwei
 * @date 2021/4/20
 */
//@Configuration
public class JwtTokenStoreConfig {

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("sso-123456");
        return converter;
    }

    @Bean
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }


    // 配置之后，可以通过该接口获取token加密公钥
    // 测试发现，配置了JwtAccessTokenConverter之后，自动暴露/oauth/token_key接口
    //@Bean
    public TokenKeyEndpoint tokenKeyEndpoint() {
        return new TokenKeyEndpoint(new JwtAccessTokenConverter());
    }
}
