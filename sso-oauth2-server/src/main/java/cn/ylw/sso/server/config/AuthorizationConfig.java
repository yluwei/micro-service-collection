package cn.ylw.sso.server.config;

import cn.ylw.sso.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * 认证配置
 *
 * @author yanluwei
 * @date 2021/4/19
 */
@Configuration
// 开启认证服务器，很重要
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private UserService userService;

    @Autowired
    private Md5PasswordEncoder md5PasswordEncoder;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 设置密码加密器，测试发现如果把md5PasswordEncoder设置为Bean，会自动注入
        security.passwordEncoder(md5PasswordEncoder);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 定义客户端连接属性
        // 这里使用内存，可以存放到数据库
        clients.inMemory()
            // 这里是clientId
            .withClient("sso")
            // 这里是client的密码，注意这里一定要使用encode加密，应为访问的时候，过滤器会自动将
            // 访问的secret进行encode后与此secret进行比较
            .secret(md5PasswordEncoder.encode("123456"))
            // 这里目前没有研究，不明白
            .scopes("read", "write")
            // 支持的授权模式，这里支持密码模式和刷新token，够用
            .authorizedGrantTypes("password", "refresh_token")
            // access_token有效期
            .accessTokenValiditySeconds(600)
            // refresh_token有效期
            .refreshTokenValiditySeconds(300);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 设置userDetailService，这里很重要，根据用户名获取用户信息并进行密码校验
        // 之前通过lamda表达式的方式配置，结果一直抛出用户名或密码不正确，打断点发现
        // 用户校验的时候用的是InMemoryUserDetailManager，所以必须以bean的形式配置
        endpoints.userDetailsService(userService)
            // 配置客户端获取token支持的method，不配置无法访问
            .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
            // 这里如果使用的是密码模式必须配置，测试发现不配置会抛出不支持passsword grant type异常
            .authenticationManager(authenticationConfiguration.getAuthenticationManager());
    }
}
