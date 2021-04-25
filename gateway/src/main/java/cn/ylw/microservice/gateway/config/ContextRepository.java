package cn.ylw.microservice.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 上下文管理
 * 由于没有使用oauth2资源服务器，需要在这里完成ServerSecurityContext的初始化，
 * 否则过滤链往下走拿不到Authentication对象，最后return new AuthorizationDecision(false)
 * 会一直响应401，而不会进入自定义的AccessDeniedHandler
 *
 * @author yanluwei
 * @date 2021/4/25
 */
@Component
public class ContextRepository implements ServerSecurityContextRepository {

    @Autowired
    private RedisTokenStore redisTokenStore;

    @Override
    public Mono<Void> save(ServerWebExchange serverWebExchange, SecurityContext securityContext) {
        // 不关心，不会用到
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        List<String> authorization = headers.get("Authorization");
        if (CollectionUtils.isEmpty(authorization)) {
            serverWebExchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return Mono.empty();
        }
        String bearer = authorization.get(0);
        if (!bearer.contains("Bearer ")) {
            return Mono.empty();
        }
        String token = bearer.replaceAll("Bearer ", "");

        OAuth2Authentication oAuth2Authentication = redisTokenStore.readAuthentication(token);
        if (oAuth2Authentication == null) {
            return Mono.empty();
        }
        return Mono.just(new SecurityContextImpl(oAuth2Authentication));
    }
}
