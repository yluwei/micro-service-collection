package cn.ylw.sso.resources.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;

/**
 * 资源服务器
 *
 * @author yanluwei
 * @date 2021/4/21
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 自定会认证失败处理
        resources.authenticationEntryPoint(new SSOAuthenticationEntryPoint());
        // 由于principal里拿不到用户id，自己重写DefaultUserAuthenticationConverter来填充用户信息
        DefaultAccessTokenConverter defaultConverter = new DefaultAccessTokenConverter();
        defaultConverter.setUserTokenConverter(new SSOAuthenticationConverter());
        jwtAccessTokenConverter.setAccessTokenConverter(defaultConverter);
    }

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//            // 配置/hello/**路径访问不需要登录
//            .antMatchers("/hello/**").permitAll()
//            // 自定义鉴权管理器
//            .anyRequest().authenticated();
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        HashMap<String, Object> principal = new HashMap<>();
        principal.put("user_id", "sso-anonymous");
        http.authorizeRequests()
            .anyRequest().authenticated().accessDecisionManager(new UrlAccessDecisionManager())
            .and().anonymous().principal(principal);
    }
}
