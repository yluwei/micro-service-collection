package cn.ylw.microservice.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * token配置
 *
 * @author yanluwei
 * @date 2021/4/23
 */
@Configuration
public class TokenStoreConfig {

    @Bean
    public RedisTokenStore tokenStore(LettuceConnectionFactory connectionFactory) {
        return new RedisTokenStore(connectionFactory);
    }
}
