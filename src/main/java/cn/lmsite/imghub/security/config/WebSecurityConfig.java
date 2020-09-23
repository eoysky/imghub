package cn.lmsite.imghub.security.config;

import cn.lmsite.imghub.common.constants.BaseConfig;
import cn.lmsite.imghub.security.CustomUserService;
import cn.lmsite.imghub.security.handle.CustomAuthenticationFailureHandler;
import cn.lmsite.imghub.security.handle.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * 设置Security安全框架的配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Resource
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    /**
     * 自定义 CustomUserService 类，实现安全框架的自带的 UserDetailsService
     */
    @Resource
    private CustomUserService customUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (BaseConfig.CLOSE_SECURITY) {
            http.csrf().disable()
                    .authorizeRequests()
                    .anyRequest().permitAll()
                    .and().logout().permitAll();
        } else {
            http.authorizeRequests()
                 //   .antMatchers("/login/invalid").permitAll()   //TODO session失效处理
//                    .antMatchers("/admin/index").hasRole("ADMIN")//指定权限为ADMIN才能访问
//                    .antMatchers("/person").hasAnyRole("ADMIN","USER")//指定多个权限都能访问
                    .anyRequest().authenticated().and()


                    .formLogin().loginPage("/login")  // TODO 设置登陆静态页面
                    .successHandler(customAuthenticationSuccessHandler) //登陆成功后自定义处理方法
                    .failureHandler(customAuthenticationFailureHandler) //登陆失败后自定义处理方法
                    .permitAll()
                    .and();
/*
                    // TODO 退出登录
                    // 1.需要使当前的 session 失效，
                    //2.清除与当前用户有关的 remember-me 记录
                    //3.清空当前的 SecurityContext
                    //4.重定向到登录页
                    .logout()
                    .logoutUrl("/signout")
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessHandler(customLogoutSuccessHandler)
                    .and();
            */


//                 //方式二 ： 配置登录失败，登陆成功默认访问路径
//                .failureUrl("/login/error")
//                .defaultSuccessUrl("/")

                 //   .sessionManagement()                     //TODO session失效处理
                 //   .invalidSessionUrl("/login/invalid")     //TODO session失效处理

    /**
                    //指定最大登录数
                    .maximumSessions(1)
                    //当达到最大值时，是否保留已经登录的用户
                    .maxSessionsPreventsLogin(false)
                    //当达到最大值时，旧用户被踢出后的操作
                    .expiredSessionStrategy(customExpiredSessionStrategy)
                    .sessionRegistry(sessionRegistry());
     */
            http.csrf().disable(); //禁用跨站请求伪造
        }

    }
}
