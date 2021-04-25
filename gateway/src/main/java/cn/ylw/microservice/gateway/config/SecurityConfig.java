package cn.ylw.microservice.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * 资源服务器配置
 *
 * @author yanluwei
 * @date 2021/4/23
 */
@Configuration
@EnableWebFluxSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final AuthorizationManager authorizationManager;

    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private final ContextRepository contextRepository;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
            .pathMatchers("/test/**").permitAll()
            .anyExchange().access(authorizationManager)
            // 自定义无权限处理器，注意，没有authentication对象时不会进入此处理器
            // 不知道在哪里进行判断的，就直接给页面响应401
            .and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
            // 初始化上下文，由于没有认证配置，所以自定义认证过程，这里非常重要
            .and().securityContextRepository(contextRepository);
        return http.build();
    }
}
