package cn.ylw.sso.config;

import cn.ylw.sso.dao.UserDao;
import cn.ylw.sso.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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

    // 认证配置
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            User user = userDao.findByUsername(username);
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            user.setAuthorities(authorities);
            return user;
        })
            .passwordEncoder(new Md5PasswordEncoder());
    }

    // 鉴权配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置不需要登录的请求
        http.authorizeRequests()
            .antMatchers("/user/**")
            .permitAll();
        // 配置任何请求需要登录
        http.authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静态资源放行
        web.ignoring().mvcMatchers("/css/**");
    }
}
