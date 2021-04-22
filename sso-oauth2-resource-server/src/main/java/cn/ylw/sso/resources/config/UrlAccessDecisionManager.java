package cn.ylw.sso.resources.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.HashMap;

/**
 * 自定义鉴权管理
 * 由于尝试了很多种方法，发现都不能满足鉴权要求，现在总结最直接的就是重写AccessDecisionManager，然后
 * 在这里写上鉴权逻辑，这里可以根据用户id去查询数据库，进行鉴权。
 * 建议使用缓存，否则每次请求都会访问数据库，导致数据库负载过大。
 *
 * @author yanluwei
 * @date 2021/4/22
 */
public class UrlAccessDecisionManager implements AccessDecisionManager {

    private static final String[] WHITE_LIST = {"/hello/**", "/money/**"};
    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        // 白名单直接放行
        FilterInvocation o1 = (FilterInvocation) o;
        String requestUrl = o1.getRequestUrl();
        for (String s : WHITE_LIST) {
            if (pathMatcher.match(s, requestUrl)) {
                return;
            }
        }
        // 获取当前用户
        Object principal = authentication.getPrincipal();
        if (principal instanceof HashMap) {
            HashMap user = (HashMap) principal;
            Object userId = user.get("user_id");
            if (userId == null || "sso-anonymous".equals(userId)) {
                throw new AccessDeniedException("请登录");
            }
            if ("1".equals(userId.toString()) && pathMatcher.match("/user/**", requestUrl)) {
                return;
            }
            if ("2".equals(userId.toString()) && pathMatcher.match("/test/**", requestUrl)) {
                return;
            }
            throw new AccessDeniedException("无访问权限");
        } else {
            throw new AccessDeniedException("无访问权限");
        }
        //
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
