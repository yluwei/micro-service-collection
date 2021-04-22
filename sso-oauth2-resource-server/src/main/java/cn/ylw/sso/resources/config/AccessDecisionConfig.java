package cn.ylw.sso.resources.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Arrays;
import java.util.List;

/**
 * 配置
 *
 * @author yanluwei
 * @date 2021/4/22
 */
//@Configuration
public class AccessDecisionConfig {
    //经过测试，自定义投票器无法与permitAll()这类的配置共存，因为无论如何都要走自定义投票器
    @Bean
    public AccessDecisionVoter<FilterInvocation> accessDecisionProcessor() {
        // 自定义投票器
        return new UrlVoter();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        // 把自定义投票器添加到AccessDecisionManager中
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        // 这一步很重要，不配置会抛出Failed to evaluate expression '#oauth2.throwOnError(authenticated)'异常
        webExpressionVoter.setExpressionHandler(new OAuth2WebSecurityExpressionHandler());
        List<AccessDecisionVoter<?>> decisionVoters = Arrays.asList(webExpressionVoter,
            accessDecisionProcessor());
        return new UnanimousBased(decisionVoters);
    }
}
