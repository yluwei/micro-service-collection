package cn.ylw.sso.resources.config;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

/**
 * 自定义投票器
 *
 * @author yanluwei
 * @date 2021/4/22
 */
public class UrlVoter implements AccessDecisionVoter<FilterInvocation> {
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation filterInvocation,
                    Collection<ConfigAttribute> collection) {
        return -1;
    }
}
