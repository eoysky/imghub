package cn.lmsite.imghub.security.config;

import cn.lmsite.imghub.security.CustomUserService;
import cn.lmsite.imghub.utils.user.MD5Util;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * 设置Security安全框架的配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 自定义 CustomUserService 类，实现安全框架的自带的 UserDetailsService
     */
    @Resource
    private CustomUserService customUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                // 加密
                return MD5Util.encode((String) rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String) rawPassword));
            }
        });
        // user Details Service 验证,加密密码对比校验
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //任何请求,登录后可以访问
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                // TODO 这里需要根据具体的登录修改一下
                .failureUrl("/login?error")
                //登录页面用户任意访问
                .permitAll()
                .and()
                //注销行为任意访问
                .logout().permitAll();
    }
}
