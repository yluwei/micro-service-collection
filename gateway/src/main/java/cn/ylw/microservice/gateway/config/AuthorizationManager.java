package cn.ylw.microservice.gateway.config;

import cn.ylw.sso.server.entity.User;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 自定义鉴权处理
 *
 * @author yanluwei
 * @date 2021/4/23
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {

        return mono.map(authentication -> {
            User principal = (User) authentication.getPrincipal();
            if (principal.getId().equals(1)) {
                return new AuthorizationDecision(true);
            } else {
                return new AuthorizationDecision(false);
            }
        });
    }

}
