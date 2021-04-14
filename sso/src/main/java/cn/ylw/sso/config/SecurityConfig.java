package cn.ylw.sso.config;

import cn.ylw.sso.dao.UserDao;
import cn.ylw.sso.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 配置
 *
 * @author yanluwei
 * @date 2021/3/24
 */
@Configuration
@EnableWebSecurity
// 配置注解@PreAuthorize("hasRole('ADMIN')")使用
// 对应的角色应该是ROLE_ADMIN
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository repository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 自动创建表
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userDao.findByUsername(username);
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getName()));
            user.setAuthorities(authorities);
            return user;
        };
    }

    // 认证配置
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
            .passwordEncoder(new Md5PasswordEncoder());
    }

    // 鉴权配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置不需要登录的请求
        http.authorizeRequests()
            .antMatchers("/user/**").permitAll();

        // 配置需要登录并且角色ROLE_ADMIN访问的路径
        http.authorizeRequests()
            .antMatchers("/admin/**")
            // 此方法自动凭借ROLE_
            .hasRole("ADMIN");

        // 配置需要登录并且角色ROLE_TEST访问的路径
        http.authorizeRequests()
            .antMatchers("/test/**")
            // 此方法需要写全称
            .hasAuthority("ROLE_TEST");

        // 配置其他请求需要登录即可访问
        http.authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin().loginPage("/login")
            .permitAll()
            .and().logout().permitAll()
            // 配置记住登录，通过cookie存储，默认两周。
            // 注意：登录一定要传递参数remember-me，否则不会自动登录
            .and().rememberMe().key("sso-token")
            // 可设置将token储存到数据库
            .tokenRepository(repository())
            .tokenValiditySeconds(100);

        // 支持同源嵌套iframe
        http.csrf().disable().headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静态资源放行
        web.ignoring().mvcMatchers("/css/**");
    }
}
